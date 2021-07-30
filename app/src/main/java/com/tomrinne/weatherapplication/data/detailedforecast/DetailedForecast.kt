package com.tomrinne.weatherapplication.data.detailedforecast

class DetailedForecast(
    val clouds: Int,
    val humidity: Int,
    val pressure: Long,
    val sunrise: Long,
    val sunset: Long,
    val visibility: Int,
    val windSpeed: Double,
    val feelsLikeTemperature: Int,
) {
    fun toList(): List<DetailForecast> {
        return listOf(
            Clouds(clouds),
            Humidity(humidity),
            Pressure(pressure),
            Visibility(visibility),
            WindSpeed(windSpeed),
            FeelsLikeTemperature(feelsLikeTemperature),
            Sunrise(sunrise),
            Sunset(sunset))
    }
}