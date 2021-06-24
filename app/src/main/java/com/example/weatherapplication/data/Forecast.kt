package com.example.weatherapplication.data

import android.content.res.Resources
import com.example.weatherapplication.R

abstract class Forecast(
    private val temperature: Int,
) {
    fun getTemperature(resources: Resources): String {
        return resources.getString(R.string.temperature, temperature)
    }

    abstract fun getTime(resources: Resources): String

}