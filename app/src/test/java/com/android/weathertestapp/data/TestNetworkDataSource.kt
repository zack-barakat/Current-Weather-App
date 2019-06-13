package com.android.weathertestapp.data

import com.android.weathertestapp.data.network.INetworkDataSource
import com.android.weathertestapp.data.network.model.WeatherResponseModel
import io.reactivex.Observable

class TestNetworkDataSource : INetworkDataSource {
    override fun getCurrentWeatherForCity(city: String): Observable<WeatherResponseModel> {
        return Observable.just(TestDataGenerator.getWeatherResponseModel())
    }
}