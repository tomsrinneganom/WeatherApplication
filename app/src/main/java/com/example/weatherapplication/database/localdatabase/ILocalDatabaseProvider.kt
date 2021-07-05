package com.example.weatherapplication.database.localdatabase

import android.content.Context
import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForDaysOfTheWeek
import kotlinx.coroutines.flow.Flow

interface ILocalDatabaseProvider {
    suspend fun getCurrentForecast(context: Context): CurrentForecast

    suspend fun getForecastForDaysOfTheWeek(context: Context): List<ForecastForDaysOfTheWeek>

    suspend fun insertCurrentForecast(context: Context, currentForecast: CurrentForecast)

    suspend fun insertForecastForDaysOfTheWeek(context: Context, forecast: List<ForecastForDaysOfTheWeek>)
}