package com.example.weatherapplication.data

import android.content.res.Resources
import com.example.weatherapplication.enums.PartOfDay

class ForecastByPartsOfTheDay(
    private val partOfTheDay: String,
    temperature: Int,
) : Forecast(temperature) {

    override fun getTime(resources: Resources): String {
        return PartOfDay.valueOf(partOfTheDay).getResourceString(resources)
    }

}