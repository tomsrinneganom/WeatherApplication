package com.tomrinne.weatherapplication.mapper

import com.tomrinne.weatherapplication.data.CurrentForecast
import com.tomrinne.weatherapplication.data.ForecastForTheDay
import org.json.JSONObject

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