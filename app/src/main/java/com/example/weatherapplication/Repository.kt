package com.example.weatherapplication

import android.util.Log
import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForTheDay
import com.example.weatherapplication.data.HourlyForecast
import com.example.weatherapplication.mapper.AbstractForecastMapper
import com.example.weatherapplication.mapper.CurrentForecastMapper
import com.example.weatherapplication.mapper.ForecastForTheDayMapper
import com.example.weatherapplication.mapper.HourlyForecastMapper
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

private const val apiKey = "c208ce39b53a16d9df2fdb2e29a36d24"

class Repository {

    private suspend fun getForecast(lat: Double, lng: Double): String {
        var json: String
        withContext(Dispatchers.IO) {
            val client = HttpClient(CIO)
            val result =
                client.get<HttpResponse>("https://api.openweathermap.org/data/2.5/onecall?lat=$lat&lon=$lng&units=metric&appid=$apiKey")
            Log.i("Log_tag", "result JSON :${result}")
            Log.i("Log_tag", "result raw body :${result.receive<String>()}")
            json = result.receive()
            client.close()
        }
        return json
    }

    suspend fun getCurrentForecast(lat: Double, lng: Double): CurrentForecast {
        val jsonObject = JSONObject(getForecast(lat, lng))
        val hourlyForecast = HourlyForecastMapper().map(jsonObject)
        return CurrentForecastMapper().map(jsonObject, hourlyForecast)
    }

    suspend fun getForecastForTheDay(lat: Double, lng: Double): List<ForecastForTheDay> {
        val json= getForecast(lat, lng)
        return ForecastForTheDayMapper().mapList(json)
    }

}