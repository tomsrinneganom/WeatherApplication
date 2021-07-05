package com.example.weatherapplication.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

data class ForecastForDaysOfTheWeek(
    @Embedded val forecastForTheDay: ForecastForTheDay,
    @Relation(
        parentColumn = "date",
        entityColumn = "date"
    ) val forecastByPartsOfTheDay: List<ForecastByPartsOfTheDay>

)