package com.example.weatherapplication.database

import android.content.Context
import android.net.ConnectivityManager
import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForDaysOfTheWeek
import com.example.weatherapplication.mapper.CurrentForecastMapper
import com.example.weatherapplication.mapper.ForecastForTheDaysOfTheWeekMapper
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class Database() : IDatabase {

    override suspend fun getCurrentForecast(lat:Double,lng:Double): CurrentForecast {
        val jsonForecast = requestForecast(lat, lng)
        return CurrentForecastMapper().map(jsonForecast)
    }

    override suspend fun getForecastForDaysOfTheWeek(lat:Double,lng:Double): List<ForecastForDaysOfTheWeek> {
        val jsonForecast = requestForecast(lat, lng)
        return ForecastForTheDaysOfTheWeekMapper().mapList(jsonForecast)
    }

    private suspend fun requestForecast(lat:Double,lng:Double): JSONObject {
        var jsonObject: JSONObject
        withContext(Dispatchers.IO) {
            val client = HttpClient(CIO)
            val result =
                client.get<HttpResponse>("https://api.openweathermap.org/data/2.5/onecall?lat=$lat&lon=$lng&units=metric&appid=$apiKey")
            val json: String = result.receive()
            client.close()

            jsonObject = JSONObject(json)
        }

        return jsonObject
    }

    companion object {
        private const val apiKey = "c208ce39b53a16d9df2fdb2e29a36d24"
    }
}