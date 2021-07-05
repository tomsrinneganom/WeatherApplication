package com.example.weatherapplication.database.localdatabase

import android.content.Context
import androidx.room.Room
import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForDaysOfTheWeek
import dagger.hilt.EntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomDatabaseProvider @Inject constructor(roomDatabase: AppDatabase) : ILocalDatabaseProvider {

    private val ioCoroutineContext = Dispatchers.IO

    private fun getRoomDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()

    }

    private fun getForecastDao(context: Context): ForecastDao {
        return getRoomDataBase(context).forecastDao()
    }

    override suspend fun getCurrentForecast(context: Context): CurrentForecast {
        return withContext(ioCoroutineContext) {
            val dao = getForecastDao(context)

            return@withContext dao.getCurrentForecast()
        }
    }

    override suspend fun getForecastForDaysOfTheWeek(context: Context): List<ForecastForDaysOfTheWeek> {
        return withContext(ioCoroutineContext) {
            val dao = getForecastDao(context)
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
            val dao = getForecastDao(context)

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
            val dao = getForecastDao(context)
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