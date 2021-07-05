package com.example.weatherapplication.mapper

import com.example.weatherapplication.data.ForecastForDaysOfTheWeek
import com.example.weatherapplication.data.ForecastForTheDay
import org.json.JSONObject

class ForecastForTheDaysOfTheWeekMapper : AbstractForecastMapper() {
    fun mapList(jsonObject: JSONObject): List<ForecastForDaysOfTheWeek> {
        val resultList = mutableListOf<ForecastForDaysOfTheWeek>()

        val jsonArray = jsonObject.getJSONArray("daily")

        for (i in 0 until jsonArray.length()) {
            val jsonItem = jsonArray.getJSONObject(i)

            val weatherIcon = getWeatherIcon(jsonItem)

            val timeInSecond = jsonItem.getLong("dt")

            val temperatureJson = jsonItem.getJSONObject("temp")
            val temperature = temperatureMapper(temperatureJson.getDouble("max"))
            val forecastForTheDay = ForecastForTheDay(timeInSecond,temperature,weatherIcon)

            resultList.add(
                ForecastForDaysOfTheWeek(
                    forecastForTheDay,
                    ForecastByPartsOfTheDayMapper().map(temperatureJson,timeInSecond)
                )
            )
        }

        return resultList
    }
}