package com.example.weatherapplication.mapper

import com.example.weatherapplication.R
import org.json.JSONObject

abstract class AbstractForecastMapper {

    protected fun temperatureMapper(temperature: Double): Int {
        return (temperature + 0.5).toInt()
    }

    protected fun getWeatherIcon(jsonObject: JSONObject): Int {
        val jsonWeather = jsonObject.getJSONArray("weather").getJSONObject(0)
        val weatherId = jsonWeather.getInt("id")
        val partOfTheDay = jsonWeather.getString("icon")[2]
        return weatherMapper(weatherId, partOfTheDay)

    }

    private fun weatherMapper(weatherId: Int, partOfTheDay: Char): Int {
        return when (weatherId) {
            in 200..232 -> R.drawable.ic_storm
            in 300..321 -> R.drawable.ic_rain
            in 500..504 -> R.drawable.ic_rain_1
            in 511..531 -> R.drawable.ic_rain_2
            601 -> R.drawable.ic_snowflake
            602, 622 -> R.drawable.ic_snowing_1
            in 600..622 -> R.drawable.ic_snowing_2
            in 701..781 -> R.drawable.ic_tornado
            800 -> {
                if (partOfTheDay == 'n')
                    R.drawable.ic_night
                else
                    R.drawable.ic_sun
            }
            801 -> {
                if (partOfTheDay == 'n') {
                    R.drawable.ic_night
                } else
                    R.drawable.ic_cloudy_1
            }
            in 802..804 -> {
                if (partOfTheDay == 'n')
                    R.drawable.ic_night
                else
                    R.drawable.ic__cloudy
            }
            else -> R.drawable.ic_cloudy_1
        }
    }

}