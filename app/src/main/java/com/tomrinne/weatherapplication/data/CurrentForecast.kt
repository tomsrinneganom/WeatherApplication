package com.tomrinne.weatherapplication.data

import androidx.room.Embedded
import androidx.room.Relation

data class CurrentForecast(
    @Embedded val forecastForTheDay: ForecastForTheDay,
    @Relation(
        parentColumn = "date",
        entityColumn = "parentDate"
    ) val hourlyForecast: List<HourlyForecast>
)