package com.tomrinne.weatherapplication.database

import android.content.Context
import android.location.Location
import android.util.Log
import com.tomrinne.weatherapplication.data.CurrentForecast
import com.tomrinne.weatherapplication.data.ForecastForDaysOfTheWeek
import com.tomrinne.weatherapplication.database.localdatabase.ILocalDatabaseProvider
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDatabaseProvider: ILocalDatabaseProvider,
    private val database: IDatabase,
) {

    suspend fun getCurrentForecast(
        locationFlow: Flow<Location?>,
        context: Context,
    ): Flow<CurrentForecast> {
        return flow {
            emit(localDatabaseProvider.getCurrentForecast(context))
            locationFlow.collect { location ->
                if (location != null) {
                    val resultFromDatabase =
                        database.getCurrentForecast(location.latitude, location.longitude)
                    if (resultFromDatabase != null) {
                        emit(resultFromDatabase)
                        localDatabaseProvider.insertCurrentForecast(context, resultFromDatabase)
                    }
                }
            }
        }

    }

    suspend fun getForecastForTheDay(
        locationFlow: Flow<Location?>,
        context: Context,
    ): Flow<List<ForecastForDaysOfTheWeek>> {

        return flow {
            emit(localDatabaseProvider.getForecastForDaysOfTheWeek(context))
            locationFlow.collect { location ->
                if (location != null) {
                    val resultFromDatabase =
                        database.getForecastForDaysOfTheWeek(location.latitude, location.longitude)
                    if (resultFromDatabase.isNotEmpty()) {
                        emit(resultFromDatabase)
                        localDatabaseProvider.insertForecastForDaysOfTheWeek(context,
                            resultFromDatabase)
                    }
                }
            }
        }

    }

    suspend fun getForecastForTheDayFromLocalDatabase(context: Context): List<ForecastForDaysOfTheWeek> {
        return localDatabaseProvider.getForecastForDaysOfTheWeek(context)
    }
    suspend fun getCurrentForecastFromLocalDatabase(context: Context):CurrentForecast{
        return localDatabaseProvider.getCurrentForecast(context)
    }

}