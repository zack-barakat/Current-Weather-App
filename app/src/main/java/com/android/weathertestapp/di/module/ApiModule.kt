package com.android.weathertestapp.di.module

import android.content.Context
import com.android.weathertestapp.BuildConfig
import com.android.weathertestapp.R
import com.android.weathertestapp.data.network.IApiHelper
import com.android.weathertestapp.data.network.IApiHelper.Factory.NETWORK_CALL_TIMEOUT
import com.android.weathertestapp.data.network.RxErrorHandlingCallAdapterFactory
import com.android.weathertestapp.di.qualifiers.ApplicationContext
import com.android.weathertestapp.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by zack_barakat
 */

@Module(includes = [ApplicationModule::class])
class ApiModule {

    @Provides
    @ApplicationScope
    internal fun provideApiHelper(retrofit: Retrofit): IApiHelper {
        return IApiHelper.Factory.create(retrofit)
    }

    @Provides
    @ApplicationScope
    fun retrofit(okHttpClient: OkHttpClient, @ApplicationContext context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.api_base_url))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create(context))
            .build()
    }

    @Provides
    @ApplicationScope
    fun okHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logging)
        builder.readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
        builder.writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
        return builder.build()
    }

    @Provides
    @ApplicationScope
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logging
    }
}
