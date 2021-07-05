package com.example.weatherapplication.mapper

import com.example.weatherapplication.data.ForecastByPartsOfTheDay
import com.example.weatherapplication.enums.EnumPartOfDay
import org.json.JSONObject

class ForecastByPartsOfTheDayMapper : AbstractForecastMapper() {

    fun map(jsonTemperature: JSONObject, parentDate: Long): List<ForecastByPartsOfTheDay> {

        val morningTemperature = temperatureMapper(jsonTemperature.getDouble("morn"))
        val dayTemperature = temperatureMapper(jsonTemperature.getDouble("day"))
        val eveningTemperature = temperatureMapper(jsonTemperature.getDouble("eve"))
        val nightTemperature = temperatureMapper(jsonTemperature.getDouble("night"))

        return listOf(
            ForecastByPartsOfTheDay(EnumPartOfDay.MORNING.name, morningTemperature, parentDate),
            ForecastByPartsOfTheDay(EnumPartOfDay.DAY.name, dayTemperature, parentDate),
            ForecastByPartsOfTheDay(EnumPartOfDay.EVENING.name, eveningTemperature, parentDate),
            ForecastByPartsOfTheDay(EnumPartOfDay.NIGHT.name, nightTemperature, parentDate)
        )
    }

}