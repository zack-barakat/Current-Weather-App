package com.android.weathertestapp.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import com.android.weathertestapp.data.db.ILocalDataSource
import com.android.weathertestapp.data.db.entity.CurrentWeatherEntity
import com.android.weathertestapp.data.network.NetworkDataSource
import com.android.weathertestapp.data.network.model.toCurrentWeatherEntity
import com.android.weathertestapp.di.qualifiers.ApplicationContext
import com.android.weathertestapp.di.scopes.ApplicationScope
import com.android.weathertestapp.utils.isNetworkAvailable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


interface IWeatherRepository {
    fun getWeatherForCity(city: String): Observable<CurrentWeatherEntity>
}

@ApplicationScope
open class WeatherRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: ILocalDataSource,
    @ApplicationContext private val context: Context
) : IWeatherRepository {

    private val currentWeatherList = mutableMapOf<String, CurrentWeatherEntity>()

    /**
     * get the current weather for selected city if
     * @return current weather for selected city
     */
    override fun getWeatherForCity(city: String): Observable<CurrentWeatherEntity> {
        return when {
            currentWeatherList[city] != null -> Observable.just(currentWeatherList[city])
            context.isNetworkAvailable() -> getWeatherFromApi(city)
            else -> getWeatherFromDb(city)
        }
    }

    private fun getWeatherFromDb(city: String): Observable<CurrentWeatherEntity> {
        return localDataSource.getCurrentWeatherForCity(city)
            .toObservable()
            .doOnNext {
                currentWeatherList[it.city] = it
            }
    }

    private fun getWeatherFromApi(city: String): Observable<CurrentWeatherEntity> {
        return networkDataSource.getCurrentWeatherForCity(city)
            .map { it.toCurrentWeatherEntity() }
            .doOnNext {
                currentWeatherList[it.city] = it
                storeUsersInDb(it)
            }
    }

    @SuppressLint("RxLeakedSubscription", "CheckResult")
    private fun storeUsersInDb(currentWeatherEntity: CurrentWeatherEntity) {
        Observable.just(currentWeatherEntity)
            .subscribeOn(Schedulers.io())
            .subscribe({
                localDataSource.insertCurrentWeather(it)
            }, {
                it.printStackTrace()
            })
    }
}