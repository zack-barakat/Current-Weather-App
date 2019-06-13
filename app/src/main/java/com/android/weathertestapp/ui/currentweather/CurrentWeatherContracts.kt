package com.android.weathertestapp.ui.currentweather

import com.android.weathertestapp.data.db.entity.CurrentWeatherEntity
import com.android.weathertestapp.ui.base.BasePresenter
import com.android.weathertestapp.ui.base.BaseView

interface CurrentWeatherContracts {

    interface View : BaseView {
        fun showSelectedCity(city: String)

        fun updateCurrentWeatherUi(currentWeatherEntity: CurrentWeatherEntity)

        fun showImageCondition(imageResource: Int)
    }

    interface Presenter<V : View> : BasePresenter<V> {
        fun onCityChanged(city: String)
    }
}
