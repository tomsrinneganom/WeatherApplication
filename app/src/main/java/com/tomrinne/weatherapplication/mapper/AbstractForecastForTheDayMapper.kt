package com.tomrinne.weatherapplication.mapper

import com.tomrinne.weatherapplication.data.detailedforecast.DetailedForecast
import org.json.JSONObject

abstract class AbstractForecastForTheDayMapper : AbstractForecastMapper() {

    fun getDetailedForecast(jsonObject: JSONObject): DetailedForecast {
        val sunrise = getSunrise(jsonObject)
        val sunset = getSunset(jsonObject)
        val feelsLikeTemperature = getFeelsLikeTemperature(jsonObject)
        val pressure = getPressure(jsonObject)
        val humidity = getHumidity(jsonObject)
        val clouds = getClouds(jsonObject)
        val visibility = getVisibility(jsonObject)
        val windSpeed = getWindSpeed(jsonObject)

        return DetailedForecast(clouds, humidity, pressure,
            sunrise,
            sunset,
            visibility, windSpeed, feelsLikeTemperature)
    }


    open fun getSunrise(jsonObject: JSONObject) =
        jsonObject.getLong("sunrise")

    open fun getSunset(jsonObject: JSONObject) =
        jsonObject.getLong("sunset")

    open fun getFeelsLikeTemperature(jsonObject: JSONObject) =
        temperatureMapper(jsonObject.getDouble("feels_like"))

    open fun getPressure(jsonObject: JSONObject) =
        jsonObject.getLong("pressure")

    open fun getHumidity(jsonObject: JSONObject) =
        jsonObject.getInt("humidity")

    open fun getClouds(jsonObject: JSONObject) =
        jsonObject.getInt("clouds")

    open fun getVisibility(jsonObject: JSONObject) =
        jsonObject.getInt("visibility")

    open fun getWindSpeed(jsonObject: JSONObject) =
        jsonObject.getDouble("wind_speed")

}