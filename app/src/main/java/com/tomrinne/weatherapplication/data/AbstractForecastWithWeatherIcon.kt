package com.tomrinne.weatherapplication.data

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

abstract class AbstractForecastWithWeatherIcon(temperature: Int, date: Long, val weatherIcon: Int) :
    AbstractForecast(temperature, date) {

    fun getDrawableWeatherIcon(context: Context): Drawable? {
        return ContextCompat.getDrawable(context, weatherIcon)
    }

    fun timeToString(time: Int): String {
        return if (time < 10) "0$time" else "$time"
    }
}