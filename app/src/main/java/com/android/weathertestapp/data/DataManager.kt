package com.android.weathertestapp.data

import android.content.Context
import com.android.weathertestapp.data.repositories.IWeatherRepository
import com.android.weathertestapp.di.qualifiers.ApplicationContext
import com.android.weathertestapp.di.scopes.ApplicationScope
import javax.inject.Inject

interface IDataManager {
    @ApplicationContext
    fun getApplicationContext(): Context

    fun getAppErrorHelper(): IAppErrorHelper

    fun getWeatherRepository(): IWeatherRepository
}

@ApplicationScope
class DataManager @Inject
constructor(
    @ApplicationContext val mApplicationContext: Context,
    private val appErrorHelper: IAppErrorHelper,
    private val weatherRepository: IWeatherRepository
) :
    IDataManager {

    override fun getApplicationContext(): Context {
        return mApplicationContext
    }

    override fun getAppErrorHelper(): IAppErrorHelper {
        return appErrorHelper
    }

    override fun getWeatherRepository(): IWeatherRepository {
        return weatherRepository
    }
}
