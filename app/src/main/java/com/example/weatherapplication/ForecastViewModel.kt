package com.example.weatherapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForTheDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForecastViewModel : ViewModel() {
    private val _forecastForTheDayState = MutableStateFlow<List<ForecastForTheDay>>(emptyList())
    private val _currentForecastState = MutableStateFlow<CurrentForecast?>(null)
    val forecastForTheDayState = _forecastForTheDayState.asStateFlow()
    val currentForecastState = _currentForecastState.asStateFlow()

    fun getForecast() {
        viewModelScope.launch {
            _currentForecastState.value =
                Repository().getCurrentForecast(46.441192, 30.740760)
            _forecastForTheDayState.value =
                Repository().getForecastForTheDay(46.441192, 30.740760)

        }
    }

    suspend fun getCurrentForecast(): CurrentForecast {

        return Repository().getCurrentForecast(46.441192, 30.740760)

    }

    suspend fun getForecastForeTheDay(): List<ForecastForTheDay> {

        return Repository().getForecastForTheDay(46.441192, 30.740760)

    }
}