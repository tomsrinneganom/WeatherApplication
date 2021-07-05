package com.example.weatherapplication

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.example.weatherapplication.data.CurrentForecast
import kotlinx.coroutines.flow.first

class ThemeProvider(private val dataStore: DataStore<Preferences>) {


    suspend fun getTheme(): Int {
        return dataStore.data.first()[THEME_PREFERENCES_KEY] ?: R.style.Theme_Cloudy
    }

    suspend fun checkMatchingThemeAndWeather(currentForecast: CurrentForecast): Boolean {
        val oldTheme = dataStore.data.first()[THEME_PREFERENCES_KEY] ?: 0

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
        dataStore.edit {
            it[THEME_PREFERENCES_KEY] = theme
        }
        return oldTheme == theme
    }

    companion object {
        private val THEME_PREFERENCES_KEY = intPreferencesKey("theme_style")
    }
}