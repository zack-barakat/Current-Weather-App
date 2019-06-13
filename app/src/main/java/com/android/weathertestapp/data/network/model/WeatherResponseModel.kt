package com.android.weathertestapp.data.network.model

import android.os.Parcelable
import com.android.weathertestapp.data.db.entity.CurrentWeatherEntity
import com.android.weathertestapp.utils.formatEpochToDateString
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

fun WeatherResponseModel.toCurrentWeatherEntity() = CurrentWeatherEntity(
    city = location.name,
    updatedTime = current.lastUpdatedEpoch.formatEpochToDateString("E h:mm a"),
    weather = current.condition.text,
    wind = "${current.windKph} km/h",
    temperature = "${current.tempC}°C",
    feelsLikeTemperature = "${current.feelsLikeC}°C",
    conditionImageUrl = current.condition.icon,
    visibility = "${current.visKm} km",
    conditionCode = current.condition.code,
    precipitation = "${current.precipMm} mm"
)

@Parcelize
data class WeatherResponseModel(
    val location: Location,
    val current: Current
) : Parcelable

@Parcelize
data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    @SerializedName("tz_id") val tzId: String,
    @SerializedName("localtime_epoch") val localtimeEpoch: Int,
    val localtime: String
) : Parcelable

@Parcelize
data class Current(
    @SerializedName("last_updated_epoch") val lastUpdatedEpoch: Long,
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("temp_f") val tempF: Double,
    @SerializedName("is_day") val isDay: Int,
    val condition: Condition,
    @SerializedName("wind_mph") val windMph: Double,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("wind_degree") val windDegree: Double,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_mb") val pressureMb: Double,
    @SerializedName("pressure_in") val pressureIn: Double,
    @SerializedName("precip_mm") val precipMm: Double,
    @SerializedName("precip_in") val precipIn: Double,
    val humidity: Int,
    val cloud: Int,
    @SerializedName("feelslike_c") val feelsLikeC: Double,
    @SerializedName("feelslike_f") val feelsLikeF: Double,
    @SerializedName("vis_km") val visKm: Double,
    @SerializedName("vis_miles") val visMiles: Double,
    val uv: Int,
    @SerializedName("gust_mph") val gustMph: Double,
    @SerializedName("gust_kph") val gustKph: Double
) : Parcelable

@Parcelize
data class Condition(
    val text: String,
    val icon: String,
    val code: Int
) : Parcelable
