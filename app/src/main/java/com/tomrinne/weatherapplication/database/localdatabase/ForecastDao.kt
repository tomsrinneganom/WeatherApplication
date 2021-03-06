package com.tomrinne.weatherapplication.database.localdatabase

import androidx.room.*
import com.tomrinne.weatherapplication.data.*

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecastForTheDay(vararg forecastForTheDay: ForecastForTheDay)

    @Transaction
    @Query("SELECT * FROM ForecastForTheDay")
    fun getCurrentForecast(): CurrentForecast

    @Transaction
    @Query("SELECT * FROM ForecastForTheDay")
    fun getForecastForDaysOfTheWeek(): List<ForecastForDaysOfTheWeek>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHourlyForecast(forecast: List<HourlyForecast>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecastByPartsOfTheDay(forecast: List<ForecastByPartsOfTheDay>)

    @Query("DELETE FROM ForecastForTheDay WHERE date != 0")
    fun deleteForecastForDaysOfTheWeek()

    @Query("DELETE FROM HourlyForecast")
    fun deleteHourlyForecast()

    @Query("DELETE FROM ForecastByPartsOfTheDay")
    fun deleteForecastByPartsOfTheDay()


}
