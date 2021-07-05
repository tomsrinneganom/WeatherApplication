package com.example.weatherapplication

import android.content.Context
import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForDaysOfTheWeek
import com.example.weatherapplication.database.Database
import com.example.weatherapplication.database.localdatabase.RoomDatabaseProvider
import dagger.hilt.EntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

class Repository @Inject constructor(private val databaseProvider: RoomDatabaseProvider){

    suspend fun getCurrentForecast(
        lat: Double,
        lng: Double,
        context: Context,
    ): Flow<CurrentForecast> {
        return flow {
            emit(databaseProvider.getCurrentForecast(context))
            val resultFromDatabase = Database(lat, lng).getCurrentForecast()
            emit(resultFromDatabase)
            databaseProvider.insertCurrentForecast(context, resultFromDatabase)
        }
    }

    suspend fun getForecastForTheDay(
        lat: Double,
        lng: Double,
        context: Context,
    ): Flow<List<ForecastForDaysOfTheWeek>> {
        return flow {
            emit(databaseProvider.getForecastForDaysOfTheWeek(context))
            val resultFromDatabase = Database(lat, lng).getForecastForDaysOfTheWeek()
            emit(resultFromDatabase)
            databaseProvider.insertForecastForDaysOfTheWeek(context, resultFromDatabase)
        }
    }

}