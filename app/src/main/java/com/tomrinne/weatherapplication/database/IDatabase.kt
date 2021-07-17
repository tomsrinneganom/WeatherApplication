package com.tomrinne.weatherapplication.database

import com.tomrinne.weatherapplication.data.CurrentForecast
import com.tomrinne.weatherapplication.data.ForecastForDaysOfTheWeek

interface IDatabase {

    suspend fun getCurrentForecast(lat:Double,lng:Double): CurrentForecast?

    suspend fun getForecastForDaysOfTheWeek(lat:Double,lng:Double): List<ForecastForDaysOfTheWeek>

}