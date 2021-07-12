package com.example.weatherapplication.data

import androidx.room.Entity

@Entity(primaryKeys = ["date"])
class HourlyForecast(
    date: Long,
    temperature: Int,
    weatherIcon: Int,
    var parentDate: Long,
) : AbstractForecastWithWeatherIcon(temperature, date, weatherIcon) {


    fun getHourAndMinute(): String {
        val dateTime = getDateTime()
        return "${timeToString(dateTime.hour)}:${timeToString(dateTime.minute)}"
    }

}

