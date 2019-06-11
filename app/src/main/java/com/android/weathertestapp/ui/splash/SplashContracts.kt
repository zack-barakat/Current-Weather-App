package com.android.weathertestapp.ui.splash

import com.android.weathertestapp.ui.base.BasePresenter
import com.android.weathertestapp.ui.base.BaseView

interface SplashContracts {

    interface View : BaseView {
        fun showMainScreen()
    }

    interface Presenter<V : View> : BasePresenter<V>
}
