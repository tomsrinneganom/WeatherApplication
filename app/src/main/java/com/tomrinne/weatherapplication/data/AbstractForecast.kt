package com.tomrinne.weatherapplication.data

import android.content.res.Resources
import com.tomrinne.weatherapplication.R
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

abstract class AbstractForecast(var temperature: Int, var date: Long) {

    protected fun getDateTime(): ZonedDateTime {
        return Instant.ofEpochSecond(date).atZone(ZoneId.systemDefault())
    }

    fun getTemperature(resources: Resources): String {
        return resources.getString(R.string.temperature, temperature)
    }

}