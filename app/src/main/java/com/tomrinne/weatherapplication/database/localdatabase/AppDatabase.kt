package com.tomrinne.weatherapplication.database.localdatabase

import androidx.room.*
import com.tomrinne.weatherapplication.data.*
import com.tomrinne.weatherapplication.data.detailedforecast.*


@Database(entities = [ForecastByPartsOfTheDay::class, ForecastForTheDay::class, HourlyForecast::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao

}