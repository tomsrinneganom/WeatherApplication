package com.example.weatherapplication.mapper

import com.example.weatherapplication.data.HourlyForecast
import org.json.JSONObject
import java.time.Instant
import java.time.ZoneId

private const val MIN_NUMBER_OF_HOURLY_FORECAST = 16

class HourlyForecastMapper : AbstractForecastMapper(){

     fun map(
        jsonObject: JSONObject
    ): List<HourlyForecast> {

        val timezone = jsonObject.getString("timezone")
        val jsonHourlyForecast = jsonObject.getJSONArray("hourly")

        val resultForecast = mutableListOf<HourlyForecast>()

        for (i in 0 until jsonHourlyForecast.length()) {
            val jsonItem = jsonHourlyForecast.getJSONObject(i)

            val time = Instant.ofEpochSecond(jsonItem.getLong("dt")).atZone(ZoneId.of(timezone))

            resultForecast.add(
                HourlyForecast(
                    time.hour,
                    time.minute,
                    temperatureMapper(jsonItem.getDouble("temp")),
                    getWeatherIcon(jsonItem)
                )
            )

            if (i >= MIN_NUMBER_OF_HOURLY_FORECAST) {
                break
            }
        }
        return resultForecast
    }

}