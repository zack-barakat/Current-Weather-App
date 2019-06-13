package com.android.weathertestapp.data

import com.android.weathertestapp.data.db.ILocalDataSource
import com.android.weathertestapp.data.db.entity.CurrentWeatherEntity
import com.android.weathertestapp.di.scopes.ApplicationScope
import io.reactivex.Flowable


@ApplicationScope
class TestLocalDataSource : ILocalDataSource {

    override fun insertCurrentWeather(weatherEntity: CurrentWeatherEntity) {

    }

    override fun getCurrentWeatherForCity(city: String): Flowable<CurrentWeatherEntity> {
        return Flowable.just(TestDataGenerator.getWeatherEntity())
    }
}
