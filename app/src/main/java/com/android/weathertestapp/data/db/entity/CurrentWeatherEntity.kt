package com.android.weathertestapp.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "current_weather")
data class CurrentWeatherEntity(
    @PrimaryKey val city: String,
    val updatedTime: String,
    val weather: String,
    val temperature: String,
    val wind: String,
    val feelsLikeTemperature: String,
    val conditionImageUrl: String,
    val conditionCode: Int,
    val visibility: String,
    val precipitation: String
) : Parcelable