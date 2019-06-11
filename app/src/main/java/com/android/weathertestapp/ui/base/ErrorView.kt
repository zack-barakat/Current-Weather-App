package com.android.weathertestapp.ui.base

import androidx.annotation.StringRes

interface ErrorView {

    fun showError(message: String, messageStyle: Int)

    fun showError(@StringRes messageResource: Int, messageStyle: Int)

    companion object {
        val ERROR_TOAST = 1
        val ERROR_DIALOG = 2
    }
}
