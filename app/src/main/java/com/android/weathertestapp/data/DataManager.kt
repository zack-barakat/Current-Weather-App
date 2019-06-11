package com.android.weathertestapp.data

import android.content.Context
import com.android.weathertestapp.di.qualifiers.ApplicationContext
import com.android.weathertestapp.di.scopes.ApplicationScope
import javax.inject.Inject

interface IDataManager {
    @ApplicationContext
    fun getApplicationContext(): Context

    fun getAppErrorHelper(): IAppErrorHelper
}

@ApplicationScope
class DataManager @Inject
constructor(@ApplicationContext val mApplicationContext: Context, private val appErrorHelper: IAppErrorHelper) :
    IDataManager {

    override fun getApplicationContext(): Context {
        return mApplicationContext
    }

    override fun getAppErrorHelper(): IAppErrorHelper {
        return appErrorHelper
    }
}
