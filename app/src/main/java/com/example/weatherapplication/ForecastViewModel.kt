package com.example.weatherapplication

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForDaysOfTheWeek
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
            val location = LocationProvider().getCurrentLocation(context)
            if (location != null) {
                launch {
                    repository.getCurrentForecast(location.latitude, location.longitude, context)
                        .collect {
                            _currentForecastState.value = it
                        }
                }
                launch {
                    repository.getForecastForTheDay(location.latitude, location.longitude, context)
                        .collect {
                            _forecastForTheDaysOfTheWeek.value = it
                        }
                }
            }
        }
    }

     fun getLocality(context: Context): StateFlow<String> {
         viewModelScope.launch {
             var locality = LocationProvider().getLocalityName(context)
             if (locality.isBlank()) {
                 locality = DataStoreManager(context.dataStore).getLastLocality()
             }
             _localityState.value = locality
         }
         return localityState
     }

}
