package com.example.weatherapplication.database.localdatabase

import androidx.room.*
import com.example.weatherapplication.data.*


@Database(entities = [ForecastByPartsOfTheDay::class, ForecastForTheDay::class, HourlyForecast::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun forecastDao(): ForecastDao

}