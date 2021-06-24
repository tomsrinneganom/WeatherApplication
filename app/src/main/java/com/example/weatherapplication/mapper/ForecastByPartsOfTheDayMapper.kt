package com.example.weatherapplication.mapper

import com.example.weatherapplication.data.ForecastByPartsOfTheDay
import com.example.weatherapplication.enums.PartOfDay
import org.json.JSONObject
import java.time.Instant
import java.time.ZoneId

class ForecastByPartsOfTheDayMapper : AbstractForecastMapper() {

    fun map(jsonTemperature: JSONObject): List<ForecastByPartsOfTheDay> {

        val morningTemperature = temperatureMapper(jsonTemperature.getDouble("morn"))
        val dayTemperature = temperatureMapper(jsonTemperature.getDouble("day"))
        val eveningTemperature = temperatureMapper(jsonTemperature.getDouble("eve"))
        val nightTemperature = temperatureMapper(jsonTemperature.getDouble("night"))

        return listOf(
            ForecastByPartsOfTheDay(PartOfDay.MORNING.name, morningTemperature),
            ForecastByPartsOfTheDay(PartOfDay.DAY.name, dayTemperature),
            ForecastByPartsOfTheDay(PartOfDay.EVENING.name, eveningTemperature),
            ForecastByPartsOfTheDay(PartOfDay.NIGHT.name, nightTemperature)
        )
    }

}