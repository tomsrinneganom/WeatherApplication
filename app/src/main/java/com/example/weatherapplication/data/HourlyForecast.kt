package com.example.weatherapplication.data

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

open class HourlyForecast(
    private val hour: Int,
    private val minute: Int,
    temperature: Int,
    protected val weatherIcon: Int,
) : Forecast(temperature) {

    override fun getTime(resources: Resources): String {
        var result = ""
        result += if (hour < 10) {
            "0$hour:"
        } else {
            "$hour:"
        }
        result += if (minute < 10) {
            "${minute}0"
        } else {
            "$minute"
        }
        return result
    }

    fun getWeatherIcon(context: Context): Drawable? {
        return ContextCompat.getDrawable(context, weatherIcon)
    }
}

