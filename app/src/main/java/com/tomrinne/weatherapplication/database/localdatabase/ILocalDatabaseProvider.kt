package com.tomrinne.weatherapplication.database.localdatabase

import android.content.Context
import com.tomrinne.weatherapplication.data.CurrentForecast
import com.tomrinne.weatherapplication.data.ForecastForDaysOfTheWeek

interface ILocalDatabaseProvider {
    suspend fun getCurrentForecast(context: Context): CurrentForecast

    suspend fun getForecastForDaysOfTheWeek(context: Context): List<ForecastForDaysOfTheWeek>

    suspend fun insertCurrentForecast(context: Context, currentForecast: CurrentForecast)

    suspend fun insertForecastForDaysOfTheWeek(context: Context, forecast: List<ForecastForDaysOfTheWeek>)
}