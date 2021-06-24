package com.example.weatherapplication.data

import android.content.res.Resources
import com.example.weatherapplication.R

abstract class AbstractForecastForTheDay(
    private val dayOfMonth: Int,
    private val dayOfWeek: Int,
    hour: Int,
    minute: Int,
    temperature: Int,
    weatherIcon: Int,
) : HourlyForecast(hour, minute, temperature, weatherIcon) {

    fun getDate(resources: Resources): String {
        return resources.getString(
            when (dayOfWeek) {
                1 -> R.string.Monday
                2 -> R.string.Tuesday
                3 -> R.string.Wednesday
                4 -> R.string.Thursday
                5 -> R.string.Friday
                6 -> R.string.Saturday
                else -> R.string.Sunday
            }, dayOfMonth)
    }

}