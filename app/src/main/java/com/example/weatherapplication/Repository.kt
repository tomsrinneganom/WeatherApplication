package com.example.weatherapplication

import android.content.Context
import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForDaysOfTheWeek
import com.example.weatherapplication.database.Database
import com.example.weatherapplication.database.IDatabase
import com.example.weatherapplication.database.localdatabase.ILocalDatabaseProvider
import com.example.weatherapplication.database.localdatabase.RoomDatabaseProvider
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDatabaseProvider: ILocalDatabaseProvider,
    private val database: IDatabase,
) {

    suspend fun getCurrentForecast(
        lat: Double,
        lng: Double,
        context: Context,
    ): Flow<CurrentForecast> {
        return flow {
            emit(localDatabaseProvider.getCurrentForecast(context))
            val resultFromDatabase = database.getCurrentForecast(lat, lng)
            emit(resultFromDatabase)
            localDatabaseProvider.insertCurrentForecast(context, resultFromDatabase)
        }
    }

    suspend fun getForecastForTheDay(
        lat: Double,
        lng: Double,
        context: Context,
    ): Flow<List<ForecastForDaysOfTheWeek>> {
        return flow {
            emit(localDatabaseProvider.getForecastForDaysOfTheWeek(context))
            val resultFromDatabase = database.getForecastForDaysOfTheWeek(lat, lng)
            emit(resultFromDatabase)
            localDatabaseProvider.insertForecastForDaysOfTheWeek(context, resultFromDatabase)
        }
    }

}