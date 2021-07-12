package com.example.weatherapplication

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForDaysOfTheWeek
import com.example.weatherapplication.database.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _forecastForTheDaysOfTheWeek =
        MutableStateFlow<List<ForecastForDaysOfTheWeek>>(emptyList())
    private val _currentForecastState = MutableStateFlow<CurrentForecast?>(null)
    private val _localityState = MutableStateFlow("")

    val forecastForTheDaysOfTheWeek = _forecastForTheDaysOfTheWeek.asStateFlow()
    val currentForecastState = _currentForecastState.asStateFlow()
    val localityState = _localityState.asStateFlow()

    fun getForecast(context: Context) {
        viewModelScope.launch {
            val locationFlow = LocationProvider().getCurrentLocation(context)

            launch {
                repository.getCurrentForecast(locationFlow, context)
                    .collect {
                        Log.i("Log_tag", "getCurrentForecast")
                        _currentForecastState.value = it
                    }
            }

            launch {
                repository.getForecastForTheDay(locationFlow, context)
                    .collect {
                        Log.i("Log_tag", "getForecastForTheDay")
                        _forecastForTheDaysOfTheWeek.value = it
                    }
            }
        }
    }


    fun getLocality(context: Context): StateFlow<String> {
        viewModelScope.launch {
            LocationProvider().getLocality(context).collect {
                _localityState.value = it
            }
        }
        return localityState
    }

}
