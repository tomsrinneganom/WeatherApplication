package com.tomrinne.weatherapplication.mapper

import com.tomrinne.weatherapplication.data.ForecastForDaysOfTheWeek
import com.tomrinne.weatherapplication.data.ForecastForTheDay
import org.json.JSONObject


class ForecastForTheDaysOfTheWeekMapper : AbstractForecastForTheDayMapper() {
    fun mapList(jsonObject: JSONObject): List<ForecastForDaysOfTheWeek> {
        val resultList = mutableListOf<ForecastForDaysOfTheWeek>()
        val jsonArray = jsonObject.getJSONArray("daily")

        for (i in 0 until jsonArray.length()) {
            val jsonItem = jsonArray.getJSONObject(i)

            val weatherIcon = getWeatherIcon(jsonItem)

            val timeInSecond = jsonItem.getLong("dt")

            val temperatureJson = jsonItem.getJSONObject("temp")
            val temperature = temperatureMapper(temperatureJson.getDouble("max"))

            val forecastForTheDay = ForecastForTheDay(timeInSecond,
                temperature,
                weatherIcon, getDetailedForecast(jsonItem))

            resultList.add(
                ForecastForDaysOfTheWeek(
                    forecastForTheDay,
                    ForecastByPartsOfTheDayMapper().map(temperatureJson, timeInSecond)
                ))

        }
        return resultList
    }

    override fun getFeelsLikeTemperature(jsonObject: JSONObject) =
        temperatureMapper(jsonObject.getJSONObject("feels_like").getDouble("day"))

    override fun getVisibility(jsonObject: JSONObject): Int {
        //TODO
        return 0
    }

}