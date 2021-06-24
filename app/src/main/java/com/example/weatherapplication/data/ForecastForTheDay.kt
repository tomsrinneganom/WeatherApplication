package com.example.weatherapplication.data

class ForecastForTheDay(
    dayOfMonth: Int,
    dayOfWeek: Int,
    hour: Int,
    minute: Int,
    temperature: Int,
    weatherIcon: Int,
    val hourlyForecast: List<ForecastByPartsOfTheDay>,
) : AbstractForecastForTheDay(dayOfMonth,
    dayOfWeek, hour, hour, temperature, weatherIcon) {

}