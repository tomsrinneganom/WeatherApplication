package com.example.weatherapplication

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForDaysOfTheWeek
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _forecastForTheDaysOfTheWeek =
        MutableStateFlow<List<ForecastForDaysOfTheWeek>>(emptyList())
    private val _currentForecastState = MutableStateFlow<CurrentForecast?>(null)

    val forecastForTheDaysOfTheWeek = _forecastForTheDaysOfTheWeek.asStateFlow()
    val currentForecastState = _currentForecastState.asStateFlow()

    fun getForecast(context: Context) {
        viewModelScope.launch {
            launch {
                repository.getCurrentForecast(46.441192, 30.740760, context).collect {
                    _currentForecastState.value = it
                }
            }
            launch {
                repository.getForecastForTheDay(46.441192, 30.740760, context).collect {
                    _forecastForTheDaysOfTheWeek.value = it
                }
            }
        }
    }
}
