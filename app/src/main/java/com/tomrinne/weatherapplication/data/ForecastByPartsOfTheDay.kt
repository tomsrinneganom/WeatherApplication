package com.tomrinne.weatherapplication.data

import android.content.res.Resources
import android.util.Log
import androidx.room.Entity
import com.tomrinne.weatherapplication.enums.EnumPartOfDay

@Entity(primaryKeys = ["date", "partOfTheDay"])
class ForecastByPartsOfTheDay(
    val partOfTheDay: String,
    temperature: Int,
    date: Long,
) : AbstractForecast(temperature, date) {

    fun getPartOfTheDay(resources: Resources): String {
        return try {
            EnumPartOfDay.valueOf(partOfTheDay).getResourceString(resources)
        } catch (e: Exception) {
            Log.e("Log_tag_ForecastByPartsOfTheDay", "getPartOfTheDay() exception: $e")
            "Error"
        }
    }
}