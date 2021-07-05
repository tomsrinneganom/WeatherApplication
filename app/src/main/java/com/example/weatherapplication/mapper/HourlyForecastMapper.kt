package com.example.weatherapplication.mapper

import com.example.weatherapplication.data.HourlyForecast
import org.json.JSONObject
import java.time.Instant
import java.time.ZoneId

private const val MIN_NUMBER_OF_HOURLY_FORECAST = 16

class HourlyForecastMapper : AbstractForecastMapper() {

    fun map(
        jsonObject: JSONObject,
        parentDate: Long,
    ): List<HourlyForecast> {

        val jsonHourlyForecast = jsonObject.getJSONArray("hourly")

        val resultForecast = mutableListOf<HourlyForecast>()

        for (i in 0 until jsonHourlyForecast.length()) {
            val jsonItem = jsonHourlyForecast.getJSONObject(i)
            val timeInSecond = jsonItem.getLong("dt")

            resultForecast.add(
                HourlyForecast(
                    timeInSecond,
                    temperatureMapper(jsonItem.getDouble("temp")),
                    getWeatherIcon(jsonItem),
                    parentDate
                )
            )

            if (i >= MIN_NUMBER_OF_HOURLY_FORECAST) {
                break
            }
        }
        return resultForecast
    }

}