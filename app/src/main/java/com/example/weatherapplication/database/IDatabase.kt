package com.example.weatherapplication.database

import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForDaysOfTheWeek

interface IDatabase {

    suspend fun getCurrentForecast(lat:Double,lng:Double): CurrentForecast

    suspend fun getForecastForDaysOfTheWeek(lat:Double,lng:Double): List<ForecastForDaysOfTheWeek>

}