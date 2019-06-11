package com.android.weathertestapp.ui.base

interface BaseView : LoadingView, ErrorView {

    fun destroyView()
}
