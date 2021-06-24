package com.example.weatherapplication.mapper

import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.HourlyForecast
import org.json.JSONObject
import java.time.Instant
import java.time.ZoneId

class CurrentForecastMapper : AbstractForecastMapper() {
    fun map(jsonObject: JSONObject, hourlyForecast: List<HourlyForecast>): CurrentForecast {

        val jsonCurrentWeather = jsonObject.getJSONObject("current")

        val timezone = jsonObject.getString("timezone")
        val time =
            Instant.ofEpochSecond(jsonCurrentWeather.getLong("dt")).atZone(ZoneId.of(timezone))

        return CurrentForecast(
            time.dayOfMonth,
            time.dayOfWeek.value,
            time.hour,
            time.minute,
            temperatureMapper(jsonCurrentWeather.getDouble("temp")),
            getWeatherIcon(jsonCurrentWeather),
            hourlyForecast
        )

    }
}