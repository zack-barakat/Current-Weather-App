package com.android.weathertestapp.data

import com.android.weathertestapp.data.network.IApiHelper
import com.android.weathertestapp.data.network.model.WeatherResponseModel
import com.android.weathertestapp.di.scopes.ApplicationScope
import io.reactivex.Observable


@ApplicationScope
class TestApiHelper : IApiHelper {


    override fun getCurrentWeatherForCity(key: String?, city: String?): Observable<WeatherResponseModel> {
        return Observable.just(TestDataGenerator.getWeatherResponseModel())
    }
}
