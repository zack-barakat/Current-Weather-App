package com.android.weathertestapp.data.db

import com.android.weathertestapp.data.db.entity.CurrentWeatherEntity
import com.android.weathertestapp.di.scopes.ApplicationScope
import io.reactivex.Flowable
import javax.inject.Inject

interface ILocalDataSource {

    fun insertCurrentWeather(weatherEntity: CurrentWeatherEntity)

    fun getCurrentWeatherForCity(city: String): Flowable<CurrentWeatherEntity>
}

@ApplicationScope
class LocalDataSource @Inject
constructor(private val mWeatherDao: WeatherDao) : ILocalDataSource {

    override fun insertCurrentWeather(weatherEntity: CurrentWeatherEntity) {
        mWeatherDao.insertWeather(weatherEntity = weatherEntity)
    }

    override fun getCurrentWeatherForCity(city: String): Flowable<CurrentWeatherEntity> {
        return mWeatherDao.getCurrentWeather(city)
    }
}
