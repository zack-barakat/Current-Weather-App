package com.android.weathertestapp.data


import com.android.weathertestapp.data.db.entity.CurrentWeatherEntity
import com.android.weathertestapp.data.network.model.WeatherResponseModel
import com.android.weathertestapp.data.network.model.toCurrentWeatherEntity
import com.google.gson.Gson

object TestDataGenerator {

    fun getWeatherResponseModel(): WeatherResponseModel {
        return Gson().fromJson(
            "{\n" +
                    "location: {\n" +
                    "name: \"Melbourne\",\n" +
                    "region: \"Victoria\",\n" +
                    "country: \"Australia\",\n" +
                    "lat: -37.82,\n" +
                    "lon: 144.97,\n" +
                    "tz_id: \"Australia/Melbourne\",\n" +
                    "localtime_epoch: 1560400484,\n" +
                    "localtime: \"2019-06-13 14:34\"\n" +
                    "},\n" +
                    "current: {\n" +
                    "last_updated_epoch: 1560399308,\n" +
                    "last_updated: \"2019-06-13 14:15\",\n" +
                    "temp_c: 16,\n" +
                    "temp_f: 60.8,\n" +
                    "is_day: 1,\n" +
                    "condition: {\n" +
                    "text: \"Partly cloudy\",\n" +
                    "icon: \"//cdn.apixu.com/weather/64x64/day/116.png\",\n" +
                    "code: 1003\n" +
                    "},\n" +
                    "wind_mph: 15,\n" +
                    "wind_kph: 24.1,\n" +
                    "wind_degree: 310,\n" +
                    "wind_dir: \"NW\",\n" +
                    "pressure_mb: 1014,\n" +
                    "pressure_in: 30.4,\n" +
                    "precip_mm: 0,\n" +
                    "precip_in: 0,\n" +
                    "humidity: 55,\n" +
                    "cloud: 25,\n" +
                    "feelslike_c: 16,\n" +
                    "feelslike_f: 60.8,\n" +
                    "vis_km: 10,\n" +
                    "vis_miles: 6,\n" +
                    "uv: 5,\n" +
                    "gust_mph: 16.1,\n" +
                    "gust_kph: 25.9\n" +
                    "}\n" +
                    "}", WeatherResponseModel::class.java
        )
    }

    fun getWeatherEntity(): CurrentWeatherEntity {
        return getWeatherResponseModel().toCurrentWeatherEntity()
    }
}
