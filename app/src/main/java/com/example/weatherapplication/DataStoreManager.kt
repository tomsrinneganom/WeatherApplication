package com.example.weatherapplication

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class DataStoreManager(private val dataStore: DataStore<Preferences>) {
    private  val themePreferencesKey = intPreferencesKey("theme_style")
    private val localityPreferencesKey = stringPreferencesKey("current_locality")

    suspend fun getTheme(): Int? {
        return dataStore.data.first()[themePreferencesKey]
    }

    suspend fun insertTheme(theme: Int) {
        dataStore.edit {
            it[themePreferencesKey] = theme
        }
    }

    suspend fun getLastLocality(): String {
        return dataStore.data.first()[localityPreferencesKey] ?: ""
    }

    suspend fun insertLastLocality(locality: String) {
        dataStore.edit {
            it[localityPreferencesKey] = locality
        }
    }
}