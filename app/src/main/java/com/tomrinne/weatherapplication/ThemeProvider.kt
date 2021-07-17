package com.tomrinne.weatherapplication

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.tomrinne.weatherapplication.data.CurrentForecast
import com.tomrinne.weatherapplication.database.DataStoreManager

class ThemeProvider(dataStore: DataStore<Preferences>) {
    private val dataStoreManager = DataStoreManager(dataStore)

    suspend fun getTheme(): Int {
        return dataStoreManager.getTheme() ?: R.style.Theme_Cloudy
    }

    suspend fun checkMatchingThemeAndWeather(currentForecast: CurrentForecast): Boolean {
        val oldTheme = getTheme()

        val theme = when (currentForecast.forecastForTheDay.weatherIcon) {
            R.drawable.ic_sun -> {
                R.style.Theme_Sun
            }
            R.drawable.ic_night -> {
                R.style.Theme_Night
            }
            else -> {
                R.style.Theme_Cloudy
            }
        }
        if (oldTheme != theme) {
            dataStoreManager.insertTheme(theme)
        }
        return oldTheme == theme
    }
}