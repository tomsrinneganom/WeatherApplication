package com.example.weatherapplication.database

import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForDaysOfTheWeek

interface IDatabase {
    val lat: Double
    val lng: Double

    suspend fun getCurrentForecast(): CurrentForecast

    suspend fun getForecastForDaysOfTheWeek(): List<ForecastForDaysOfTheWeek>

}