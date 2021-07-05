package com.example.weatherapplication.data

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

abstract class AbstractForecastWithWeatherIcon(temperature: Int, date: Long, val weatherIcon: Int) :
    AbstractForecast(temperature, date) {

    fun getDrawableWeatherIcon(context: Context): Drawable? {
        return ContextCompat.getDrawable(context, weatherIcon)
    }

}