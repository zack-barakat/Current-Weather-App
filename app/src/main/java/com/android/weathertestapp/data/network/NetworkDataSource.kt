package com.android.weathertestapp.data.network

import android.content.Context
import com.android.weathertestapp.R
import com.android.weathertestapp.data.network.model.WeatherResponseModel
import com.android.weathertestapp.di.qualifiers.ApplicationContext
import com.android.weathertestapp.di.scopes.ApplicationScope
import io.reactivex.Observable
import javax.inject.Inject

interface INetworkDataSource {
    fun getCurrentWeatherForCity(city: String): Observable<WeatherResponseModel>
}

@ApplicationScope
class NetworkDataSource @Inject
constructor(private val apiHelper: IApiHelper, @ApplicationContext val context: Context) : INetworkDataSource {

    override fun getCurrentWeatherForCity(city: String): Observable<WeatherResponseModel> {
        return apiHelper.getCurrentWeatherForCity(context.getString(R.string.apixu_api_key), city)
    }
}
