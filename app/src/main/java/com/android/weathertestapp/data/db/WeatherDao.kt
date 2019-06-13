package com.android.weathertestapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.weathertestapp.data.db.entity.CurrentWeatherEntity
import io.reactivex.Flowable

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weatherEntity: CurrentWeatherEntity)

    @Query("select * from current_weather where city = :city")
    fun getCurrentWeather(city: String): Flowable<CurrentWeatherEntity>
}