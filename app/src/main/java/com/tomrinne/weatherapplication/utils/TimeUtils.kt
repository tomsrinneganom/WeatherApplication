package com.tomrinne.weatherapplication.utils

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class TimeUtils {
    fun getDateTime(time: Long): ZonedDateTime {
        return Instant.ofEpochSecond(time).atZone(ZoneId.systemDefault())
    }

    fun getHourAndMinute(time: Long): String {
        val dateTime = getDateTime(time)
        return "${timeToString(dateTime.hour)}:${timeToString(dateTime.minute)}"
    }

    fun timeToString(time: Int): String {
        return if (time < 10) "0$time" else "$time"
    }
}