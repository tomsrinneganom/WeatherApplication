package com.example.weatherapplication.data

import android.content.res.Resources
import androidx.room.Entity
import com.example.weatherapplication.R


@Entity(primaryKeys = ["date"])
class ForecastForTheDay(
    date: Long,
    temperature: Int,
    weatherIcon: Int,
) : AbstractForecastWithWeatherIcon(temperature, date, weatherIcon) {

    fun getDate(resources: Resources): String {
        return resources.getString(
            when (getDateTime().dayOfWeek.value) {
                1 -> R.string.Monday
                2 -> R.string.Tuesday
                3 -> R.string.Wednesday
                4 -> R.string.Thursday
                5 -> R.string.Friday
                6 -> R.string.Saturday
                else -> R.string.Sunday
            }, getDateTime().dayOfMonth)
    }

    companion object{
        const val DATE_CURRENT_FORECAST:Long = 0
    }
}