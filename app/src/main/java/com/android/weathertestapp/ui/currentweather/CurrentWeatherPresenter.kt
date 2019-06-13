package com.android.weathertestapp.ui.currentweather

import com.android.weathertestapp.R
import com.android.weathertestapp.data.IDataManager
import com.android.weathertestapp.data.WeatherConditions
import com.android.weathertestapp.data.db.entity.CurrentWeatherEntity
import com.android.weathertestapp.ui.base.BaseMvpPresenter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrentWeatherPresenter @Inject
constructor(dataManager: IDataManager) : BaseMvpPresenter<CurrentWeatherContracts.View>(dataManager),
    CurrentWeatherContracts.Presenter<CurrentWeatherContracts.View> {

    override fun onAttachView(view: CurrentWeatherContracts.View) {
        super.onAttachView(view)
    }

    override fun onCityChanged(city: String) {
        view.showSelectedCity(city)
        view.showProgress()
        val disposable = weatherRepository.getWeatherForCity(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                processCurrentWeather(it)
            }, {
                view.hideProgress()
                it.printStackTrace()
            })
        addToSubscription(disposable)
    }

    private fun processCurrentWeather(currentWeatherEntity: CurrentWeatherEntity) {
        view.updateCurrentWeatherUi(currentWeatherEntity)
        val disposable = Observable.just(currentWeatherEntity)
            .map { currentWeather ->
                val weatherConditionsText = mAppContext.resources
                    .openRawResource(R.raw.apixu_weather_conditions)
                    .bufferedReader().use { it.readText() }
                val weatherConditionsType = object : TypeToken<ArrayList<WeatherConditions>>() {}.type
                val weatherConditionsArray =
                    Gson().fromJson<ArrayList<WeatherConditions>>(weatherConditionsText, weatherConditionsType)
                weatherConditionsArray.first { it.code == currentWeather.conditionCode }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val iconResourceName = "ic_${it.icon}"
                val resourceId =
                    mAppContext.resources.getIdentifier(iconResourceName, "drawable", mAppContext.packageName)
                view.showImageCondition(resourceId)
            }, {
                it.printStackTrace()
            })
        addToSubscription(disposable)
    }
}
