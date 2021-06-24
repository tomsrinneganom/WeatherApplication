package com.example.weatherapplication.mapper

import com.example.weatherapplication.data.ForecastByPartsOfTheDay
import com.example.weatherapplication.data.ForecastForTheDay
import com.example.weatherapplication.enums.PartOfDay
import org.json.JSONObject
import java.time.Instant
import java.time.ZoneId

class ForecastForTheDayMapper : AbstractForecastMapper() {
    fun mapList(json: String): List<ForecastForTheDay> {
        val resultList = mutableListOf<ForecastForTheDay>()

        val jsonObject = JSONObject(json)
        val jsonArray = jsonObject.getJSONArray("daily")

        val timezone = jsonObject.getString("timezone")

        for (i in 0 until jsonArray.length()) {
            val jsonItem = jsonArray.getJSONObject(i)

            val weatherIcon = getWeatherIcon(jsonItem)

            val time = Instant.ofEpochSecond(jsonItem.getLong("dt")).atZone(ZoneId.of(timezone))

            val temperatureJson = jsonItem.getJSONObject("temp")
            val temperature = temperatureMapper(temperatureJson.getDouble("max"))

            resultList.add(
                ForecastForTheDay(
                    time.dayOfMonth,
                    time.dayOfWeek.value,
                    time.hour,
                    time.minute,
                    temperature,
                    weatherIcon,
                    ForecastByPartsOfTheDayMapper().map(temperatureJson)
                )
            )
        }

        return resultList
    }
}