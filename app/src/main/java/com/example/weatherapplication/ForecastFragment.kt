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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.adapter.ListByDayAdapter
import com.example.weatherapplication.adapter.HourlyForecastAdapter
import com.example.weatherapplication.data.CurrentForecast
import com.example.weatherapplication.data.ForecastForTheDay
import com.example.weatherapplication.extensions.recreateSmoothly
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ForecastFragment : Fragment() {

    private val viewModel: ForecastViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var currentTemperatureTextView: TextView
    private lateinit var hourlyForecastAdapter: HourlyForecastAdapter
    private lateinit var listByDayAdapter: ListByDayAdapter
    private lateinit var currentWeatherImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forecast, container, false)
        currentTemperatureTextView = view.findViewById(R.id.degreesCelsiusTextView)
        currentWeatherImageView = view.findViewById(R.id.weatherIconsImageView)
        recyclerView = view.findViewById(R.id.listByDateRecyclerView)

        recyclerView2 = view.findViewById(R.id.listByDayRecyclerView)


        lifecycleScope.launch {
//            viewModel.getForecast()
//
//            viewModel.currentForecastState.flowWithLifecycle(lifecycle,Lifecycle.State.STARTED).collect { forecast ->
//                if (forecast != null) {
//                    Log.i("Log_tag", "forecast != null)")
//                    bindCurrentWeather(forecast)
//                } else
//                    Log.i("Log_tag", "forecast == null)")
//            }
//            viewModel.forecastForTheDayState.flowWithLifecycle(lifecycle,Lifecycle.State.STARTED) .collect { forecastList ->
//                bindForecastForTheDay(forecastList)
//            }
            bindCurrentWeather(viewModel.getCurrentForecast())
            bindForecastForTheDay(viewModel.getForecastForeTheDay())
        }

        return view
    }

    private fun bindCurrentWeather(forecast: CurrentForecast) {
        Log.i("Log_tag", "bindCurrentWeather")
        currentTemperatureTextView.apply {
            isClickable = true
            setOnClickListener {
                ThemeProvider.switchColor()
                activity?.recreateSmoothly()

            }
        }
        currentTemperatureTextView.text = forecast.getTemperature(resources)

        currentWeatherImageView.setImageDrawable(forecast.getWeatherIcon(requireContext()))

        hourlyForecastAdapter = HourlyForecastAdapter(forecast.hourlyForecast)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = hourlyForecastAdapter

    }

    private fun bindForecastForTheDay(forecastForTheDay: List<ForecastForTheDay>) {
        listByDayAdapter = ListByDayAdapter(forecastForTheDay)
        recyclerView2.adapter = listByDayAdapter
        recyclerView2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }
}