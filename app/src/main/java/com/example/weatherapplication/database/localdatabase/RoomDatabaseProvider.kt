package com.example.weatherapplication.database.localdatabase

import android.content.Context
import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForDaysOfTheWeek
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomDatabaseProvider @Inject constructor(private val roomDatabase: AppDatabase) : ILocalDatabaseProvider {

    private val ioCoroutineContext = Dispatchers.IO

    private fun getForecastDao(): ForecastDao {
        return roomDatabase.forecastDao()
    }

    override suspend fun getCurrentForecast(context: Context): CurrentForecast {
        return withContext(ioCoroutineContext) {
            val dao = getForecastDao()
            return@withContext dao.getCurrentForecast()
        }
    }

    override suspend fun getForecastForDaysOfTheWeek(context: Context): List<ForecastForDaysOfTheWeek> {
        return withContext(ioCoroutineContext) {
            val dao = getForecastDao()
            val forecast = dao.getForecastForDaysOfTheWeek().toMutableList()
            forecast.removeIf {
                it.forecastByPartsOfTheDay.isEmpty()
            }
            forecast.sortBy {
                it.forecastForTheDay.date
            }
            return@withContext forecast
        }
    }

    override suspend fun insertCurrentForecast(context: Context, currentForecast: CurrentForecast) {
        withContext(ioCoroutineContext) {
            val dao = getForecastDao()

            dao.deleteHourlyForecast()
            dao.insertHourlyForecast(currentForecast.hourlyForecast)
            dao.insertForecastForTheDay(currentForecast.forecastForTheDay)
        }
    }

    override suspend fun insertForecastForDaysOfTheWeek(
        context: Context,
        forecast: List<ForecastForDaysOfTheWeek>,
    ) {
        withContext(ioCoroutineContext) {
            val dao = getForecastDao()
            if (forecast.isNotEmpty()) {
                dao.deleteForecastForDaysOfTheWeek()
                dao.deleteForecastByPartsOfTheDay()

                forecast.forEach {
                    dao.insertForecastForTheDay(it.forecastForTheDay)
                    dao.insertForecastByPartsOfTheDay(it.forecastByPartsOfTheDay)
                }
            }
        }
    }

    companion object {
        const val DATABASE_NAME = "forecast-database"
    }
}