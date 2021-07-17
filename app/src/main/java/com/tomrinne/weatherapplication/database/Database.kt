package com.tomrinne.weatherapplication.database

import android.util.Log
import com.tomrinne.weatherapplication.BuildConfig
import com.tomrinne.weatherapplication.data.CurrentForecast
import com.tomrinne.weatherapplication.data.ForecastForDaysOfTheWeek
import com.tomrinne.weatherapplication.mapper.CurrentForecastMapper
import com.tomrinne.weatherapplication.mapper.ForecastForTheDaysOfTheWeekMapper
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception

class Database() : IDatabase {

    override suspend fun getCurrentForecast(lat: Double, lng: Double): CurrentForecast? {
        val jsonForecast = requestForecast(lat, lng)

        return if (jsonForecast != null) {
            CurrentForecastMapper().map(jsonForecast)
        } else {
            null
        }
    }

    override suspend fun getForecastForDaysOfTheWeek(
        lat: Double,
        lng: Double,
    ): List<ForecastForDaysOfTheWeek> {
        val jsonForecast = requestForecast(lat, lng)
        return if (jsonForecast != null) {
            ForecastForTheDaysOfTheWeekMapper().mapList(jsonForecast)
        } else
            emptyList()
    }

    private suspend fun requestForecast(lat: Double, lng: Double): JSONObject? {
        var jsonObject: JSONObject
        return try {
            withContext(Dispatchers.IO) {
                val client = HttpClient(CIO)
                val result =
                    client.get<HttpResponse>("https://api.openweathermap.org/data/2.5/onecall?lat=$lat&lon=$lng&units=metric&appid=${BuildConfig.openWeatherApiKey}")

                val json: String = result.receive()
                client.close()

                jsonObject = JSONObject(json)
            }
            jsonObject
        } catch (e: Exception) {
            Log.i("Log_tag","exception ${e.message}")
            null
        }
    }
}