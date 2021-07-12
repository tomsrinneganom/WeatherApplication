package com.example.weatherapplication.mapper

import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForTheDay
import com.example.weatherapplication.data.HourlyForecast
import org.json.JSONObject
import java.time.Instant
import java.time.ZoneId

class CurrentForecastMapper : AbstractForecastMapper() {
    fun map(jsonObject: JSONObject): CurrentForecast {
        val jsonCurrentWeather = jsonObject.getJSONObject("current")

        val forecastForTheDay = ForecastForTheDay(ForecastForTheDay.DATE_CURRENT_FORECAST,temperatureMapper(jsonCurrentWeather.getDouble("temp")),getWeatherIcon(jsonCurrentWeather))

        val hourlyForecast = HourlyForecastMapper().map(jsonObject,ForecastForTheDay.DATE_CURRENT_FORECAST)

        return CurrentForecast(
            forecastForTheDay,
            hourlyForecast
        )

    }
}