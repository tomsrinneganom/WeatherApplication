package com.example.weatherapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.adapter.HourlyForecastAdapter
import com.example.weatherapplication.adapter.ListByDayAdapter
import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForDaysOfTheWeek
import com.example.weatherapplication.extensions.collect
import com.example.weatherapplication.extensions.recreateSmoothly
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ForecastFragment : Fragment() {

    private val viewModel: ForecastViewModel by viewModels()
    private var hourlyForecastRecyclerView: RecyclerView? = null
    private var forecastForTheDayRecyclerView: RecyclerView? = null
    private var currentTemperatureTextView: TextView? = null
    private var hourlyForecastAdapter: HourlyForecastAdapter? = null
    private var forecastForTheDayAdapter: ListByDayAdapter? = null
    private var currentWeatherImageView: ImageView? = null
    private var localityTextView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forecast, container, false)

        currentTemperatureTextView = view.findViewById(R.id.degreesCelsiusTextView)
        currentWeatherImageView = view.findViewById(R.id.weatherIconsImageView)
        hourlyForecastRecyclerView = view.findViewById(R.id.listByDateRecyclerView)
        forecastForTheDayRecyclerView = view.findViewById(R.id.listByDayRecyclerView)
        localityTextView = view.findViewById(R.id.localityTextView)


        viewModel.getLocality(requireContext()).onEach {
            localityTextView?.text = it
        }.collect(lifecycle)

        return view
    }

    override fun onResume() {
        super.onResume()
        getForecast()
    }

    private fun bindForecastForTheDay(forecastForTheDay: List<ForecastForDaysOfTheWeek>) {
        forecastForTheDayAdapter = ListByDayAdapter(forecastForTheDay)
        forecastForTheDayRecyclerView?.apply {
            adapter = forecastForTheDayAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun bindCurrentWeather(forecast: CurrentForecast) {
        currentTemperatureTextView?.text = forecast.forecastForTheDay.getTemperature(resources)

        currentWeatherImageView?.setImageDrawable(forecast.forecastForTheDay.getDrawableWeatherIcon(
            requireContext()))

        hourlyForecastAdapter = HourlyForecastAdapter(forecast.hourlyForecast)
        hourlyForecastRecyclerView?.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = hourlyForecastAdapter
        }
    }

    private fun getForecast() {
        viewModel.getForecast(requireContext())

        viewModel.currentForecastState.onEach {
            if (it != null) {
                if (!ThemeProvider(requireContext().dataStore).checkMatchingThemeAndWeather(it)) {
                    activity?.recreateSmoothly()
                }
                bindCurrentWeather(it)
            }
        }.collect(lifecycle)

        viewModel.forecastForTheDaysOfTheWeek.onEach {
            if (it.isNotEmpty()) {
                bindForecastForTheDay(it)
            }
        }.collect(lifecycle)

    }
}

