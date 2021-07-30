package com.tomrinne.weatherapplication.data

import android.content.res.Resources
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import com.tomrinne.weatherapplication.R
import com.tomrinne.weatherapplication.data.detailedforecast.DetailedForecast


@Entity(primaryKeys = ["date"])
class ForecastForTheDay(
    date: Long,
    temperature: Int,
    weatherIcon: Int,
    @Embedded
    val detailedForecast: DetailedForecast,
) : AbstractForecastWithWeatherIcon(temperature, date, weatherIcon) {


    @Ignore
    val detailForecastList =
        detailedForecast.toList()

    fun getDate(resources: Resources): String {
        val dateTime = getDateTime()

        return resources.getString(
            when (dateTime.dayOfWeek.value) {
                1 -> R.string.Monday
                2 -> R.string.Tuesday
                3 -> R.string.Wednesday
                4 -> R.string.Thursday
                5 -> R.string.Friday
                6 -> R.string.Saturday
                else -> R.string.Sunday
            }, timeToString(dateTime.dayOfMonth))

    }

    companion object {
        const val DATE_OF_THE_CURRENT_FORECAST: Long = 0
    }
}