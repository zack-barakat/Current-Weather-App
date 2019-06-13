package com.android.weathertestapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.weathertestapp.data.db.entity.CurrentWeatherEntity

@Database(entities = [CurrentWeatherEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun currentWeatherDao(): WeatherDao

    companion object {
        private const val DATABASE_NAME = "weather.db"
        @Volatile
        private var instance: WeatherDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): WeatherDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                WeatherDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}