package com.example.weatherapplication

import android.location.Location
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreManager(private val dataStore: DataStore<Preferences>) {
    private val themePreferencesKey = intPreferencesKey("theme_style")
    private val localityPreferencesKey = stringPreferencesKey("current_locality")
    private val latitudePreferencesKey = doublePreferencesKey("last_location_latitude")
    private val longitudePreferencesKey = doublePreferencesKey("last_longitude_latitude")

    suspend fun getTheme(): Int? {
        return dataStore.data.first()[themePreferencesKey]
    }

    suspend fun insertTheme(theme: Int) {
        dataStore.edit {
            it[themePreferencesKey] = theme
        }
    }

    suspend fun getLastLocation(): Location? {
        val latitude = dataStore.data.first()[latitudePreferencesKey]
        val longitude = dataStore.data.first()[longitudePreferencesKey]
        return if (longitude == null || latitude == null) {
            null
        } else {
            val location = Location("lastLocation").apply {
                this.latitude = latitude
                this.longitude = longitude
            }
            location
        }
    }

    suspend fun insertLastLocation(location: Location){
        dataStore.edit {
            it[latitudePreferencesKey] = location.latitude
            it[longitudePreferencesKey] = location.longitude
        }
    }
}