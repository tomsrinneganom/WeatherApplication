package com.example.weatherapplication.utils

import android.content.res.Resources
import com.example.weatherapplication.R

class ForecastUtils() {
    fun temperatureToString(resources: Resources, temperature: Int): String {
        return resources.getString(R.string.temperature, temperature)
    }

    fun dateToString(resources: Resources, dayOfMoth: Int, dayOfWeek: Int): String {
        return resources.getString(
            when (dayOfWeek) {
                1 -> R.string.Monday
                2 -> R.string.Tuesday
                3 -> R.string.Wednesday
                4 -> R.string.Thursday
                5 -> R.string.Friday
                6 -> R.string.Saturday
                else -> R.string.Sunday
            }, dayOfMoth)

    }

    fun getHoursAndMinutes(hour: Int, minute: Int): String {
        var result = ""
        result += if (hour < 10) {
            "0$hour:"
        } else {
            "$hour:"
        }
        result += if (hour < 10) {
            "${hour}0"
        } else {
            "$hour"
        }
        return result
    }
}