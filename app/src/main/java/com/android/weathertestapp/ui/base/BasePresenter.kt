package com.android.weathertestapp.ui.base

import io.reactivex.disposables.Disposable

interface BasePresenter<V : BaseView> {

    var isViewPaused: Boolean

    fun addToSubscription(disposable: Disposable)

    fun onResume()

    fun onDestroyView()

    fun onAttachView(view: V)
}
