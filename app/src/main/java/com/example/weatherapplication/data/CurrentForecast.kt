package com.example.weatherapplication.data

class CurrentForecast(
    dayOfMonth: Int,
    dayOfWeek: Int,
    hour: Int,
    minute: Int,
    temperature: Int,
    weatherIcon: Int,
    val hourlyForecast: List<HourlyForecast>,
) : AbstractForecastForTheDay(dayOfMonth, dayOfWeek, hour, minute, temperature, weatherIcon)