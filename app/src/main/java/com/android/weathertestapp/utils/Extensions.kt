package com.android.weathertestapp.utils

import android.content.Context
import android.net.ConnectivityManager
import java.text.SimpleDateFormat
import java.util.*

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
}

fun Long.formatEpochToDateString(formatString: String): String {
    val date = Date(this * 1000)
    val format = SimpleDateFormat(formatString)
    return format.format(date)
}