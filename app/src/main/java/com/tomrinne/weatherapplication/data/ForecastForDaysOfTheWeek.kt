package com.tomrinne.weatherapplication.data

import androidx.room.Embedded
import androidx.room.Relation

data class ForecastForDaysOfTheWeek(
    @Embedded val forecastForTheDay: ForecastForTheDay,
    @Relation(
        parentColumn = "date",
        entityColumn = "date"
    ) val forecastByPartsOfTheDay: List<ForecastByPartsOfTheDay>

)