package com.example.weatherapplication

object ThemeProvider {

    private var settingsData = 0

    fun getColoredTheme(): Int {
        return when (settingsData) {
            0 -> {
                R.style.Theme_Cloudy
            }
            1 -> {
                R.style.Theme_Sun
            }
            else -> R.style.Theme_Night
        }
    }

    fun switchColor() {
        settingsData = if (settingsData + 1 > 2) {
            0
        } else {
            settingsData + 1
        }
    }

}