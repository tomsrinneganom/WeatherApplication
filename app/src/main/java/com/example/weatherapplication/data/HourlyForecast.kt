package com.example.weatherapplication.data

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@Entity(primaryKeys = ["date"])
class HourlyForecast(
    date: Long,
    temperature: Int,
    weatherIcon: Int,
    var parentDate: Long,
) : AbstractForecastWithWeatherIcon(temperature, date, weatherIcon) {


    fun getHourAndMinute(): String {
        val dateTime = getDateTime()
        val hour = dateTime.hour
        val minute = dateTime.minute

        var result = ""
        result += if (hour < 10) "0$hour:" else "$hour:"
        result += if (minute < 10) "${minute}0" else "$minute"

        return result
    }

}

