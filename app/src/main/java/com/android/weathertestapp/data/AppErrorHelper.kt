package com.android.weathertestapp.data

import android.content.Context
import androidx.annotation.NonNull
import com.android.weathertestapp.R
import com.android.weathertestapp.data.model.CommonResponseModel
import com.android.weathertestapp.data.network.RetrofitException
import com.android.weathertestapp.di.qualifiers.ApplicationContext
import com.android.weathertestapp.di.scopes.ApplicationScope
import javax.inject.Inject


interface IAppErrorHelper {
    @NonNull
    fun handleAppException(exception: Throwable): ErrorAction

    fun getErrorMessage(throwable: Throwable): String

    fun getErrorCode(throwable: Throwable): String?

}

@ApplicationScope
class AppErrorHelper @Inject
constructor(@ApplicationContext val mAppContext: Context) : IAppErrorHelper {

    override fun handleAppException(exception: Throwable): ErrorAction {
        if (exception !is RetrofitException) {
            return getUnExpectedErrorAction()
        }
        when (exception.kind) {
            RetrofitException.Kind.NETWORK -> return ErrorAction(
                ErrorAction.ACTION_TYPE_SHOW_ERROR,
                mAppContext.getString(R.string.title_information),
                mAppContext.getString(R.string.message_connection_error)
            )
            RetrofitException.Kind.HTTP -> {
                val apiError = exception.errorBodyAsCommonResponseModel ?: return getUnExpectedErrorAction()
                return handleCommonResponseModel(apiError)
            }
            RetrofitException.Kind.DISCONNECT -> return ErrorAction(
                ErrorAction.ACTION_TYPE_SHOW_ERROR,
                mAppContext.getString(R.string.title_information),
                mAppContext.getString(R.string.message_connect_internet)
            )
            RetrofitException.Kind.UNEXPECTED -> return getUnExpectedErrorAction()
        }
    }

    override fun getErrorMessage(throwable: Throwable): String {
        if (throwable !is RetrofitException) {
            return getUnExpectedErrorAction().messageContent
        }
        return when (throwable.kind) {
            RetrofitException.Kind.NETWORK -> mAppContext.getString(R.string.message_connection_error)
            RetrofitException.Kind.HTTP -> {
                val apiError = throwable.errorBodyAsCommonResponseModel
                if (apiError?.message == null) {
                    getUnExpectedErrorAction().messageContent
                } else apiError.message ?: mAppContext.getString(R.string.message_error_unknown)
            }
            RetrofitException.Kind.DISCONNECT -> mAppContext.getString(R.string.message_connect_internet)
            RetrofitException.Kind.UNEXPECTED -> getUnExpectedErrorAction().messageContent
        }
    }

    override fun getErrorCode(throwable: Throwable): String? {
        if (throwable !is RetrofitException) {
            return null
        }
        if (throwable.kind == RetrofitException.Kind.HTTP) {
            val apiError = throwable.errorBodyAsCommonResponseModel
            return if (apiError?.message == null) {
                null
            } else apiError.message
        }
        return null
    }

    private fun handleCommonResponseModel(apiError: CommonResponseModel): ErrorAction {
        return if (apiError.message == null) {
            getUnExpectedErrorAction()
        } else ErrorAction(
            ErrorAction.ACTION_TYPE_SHOW_ERROR,
            mAppContext.getString(R.string.title_information),
            apiError.message
        )
    }

    private fun getUnExpectedErrorAction(): ErrorAction {
        return ErrorAction(
            ErrorAction.ACTION_TYPE_SHOW_ERROR,
            mAppContext.getString(R.string.title_information),
            mAppContext.getString(R.string.message_error_unknown)
        )
    }
}
