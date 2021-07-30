package com.tomrinne.weatherapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.color.MaterialColors
import com.tomrinne.weatherapplication.adapter.DetailedForecastAdapter
import com.tomrinne.weatherapplication.adapter.ForecastForDaysOfTheWeekAdapter
import com.tomrinne.weatherapplication.adapter.HourlyForecastAdapter
import com.tomrinne.weatherapplication.data.CurrentForecast
import com.tomrinne.weatherapplication.data.ForecastForDaysOfTheWeek
import com.tomrinne.weatherapplication.extensions.collect
import com.tomrinne.weatherapplication.extensions.recreateSmoothly
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ForecastFragment : Fragment() {

    private val viewModel: ForecastViewModel by viewModels()
    private var hourlyForecastRecyclerView: RecyclerView? = null
    private var forecastForTheDayRecyclerView: RecyclerView? = null
    private var currentTemperatureTextView: TextView? = null
    private var hourlyForecastAdapter: HourlyForecastAdapter? = null
    private var forecastForTheDayAdapterAdapter: ForecastForDaysOfTheWeekAdapter? = null
    private var detailedForecastAdapter: DetailedForecastAdapter? = null
    private var currentWeatherImageView: ImageView? = null
    private var localityTextView: TextView? = null
    private var cardView: CardView? = null

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
        cardView = view.findViewById(R.id.cardView)

        initSwipeRefresh(view)

        forecastForTheDayAdapterAdapter = ForecastForDaysOfTheWeekAdapter()
        detailedForecastAdapter = DetailedForecastAdapter()

        forecastForTheDayRecyclerView?.apply {
            adapter = forecastForTheDayAdapterAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        hourlyForecastAdapter = HourlyForecastAdapter()
        hourlyForecastRecyclerView?.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = hourlyForecastAdapter
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        getLocality()
        getForecast()
    }

    private fun initSwipeRefresh(view: View) {
        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        swipeRefreshLayout.setColorSchemeColors(MaterialColors.getColor(view,
            R.attr.colorTextPrimary))

        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(MaterialColors.getColor(view,
            R.attr.colorBackgroundSecondary))

        swipeRefreshLayout.setOnRefreshListener {
            getForecast()
            getLocality()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun updateForecastForTheDay(forecastForTheDay: List<ForecastForDaysOfTheWeek>) {
        Log.i("Log_tag", "hourlyForecastRecyclerView?.setOnClickListener")
        forecastForTheDayAdapterAdapter?.updateList(forecastForTheDay)

    }

    private fun updateCurrentWeather(forecast: CurrentForecast) {
        detailedForecastAdapter?.update(forecast.forecastForTheDay.detailForecastList)
        hourlyForecastAdapter?.updateList(forecast.hourlyForecast)


        cardView?.let {
            it.isClickable = true
            it.setOnClickListener {
                if (hourlyForecastRecyclerView?.adapter == hourlyForecastAdapter) {
                    hourlyForecastRecyclerView?.layoutManager =
                        GridLayoutManager(requireContext(), 2)
                    hourlyForecastRecyclerView?.adapter = detailedForecastAdapter
                } else {
                    hourlyForecastRecyclerView?.layoutManager =
                        LinearLayoutManager(requireContext())
                    hourlyForecastRecyclerView?.adapter = hourlyForecastAdapter
                }
            }
        }
        currentTemperatureTextView?.text = forecast.forecastForTheDay.getTemperature(resources)

        currentWeatherImageView?.setImageDrawable(forecast.forecastForTheDay.getDrawableWeatherIcon(
            requireContext()))

        hourlyForecastAdapter?.updateList(forecast.hourlyForecast)
    }

    private fun getLocality() {
        viewModel.getLocality(requireContext()).onEach {
            localityTextView?.text = it
        }.collect(lifecycle)
    }

    private fun getForecast() {

        viewModel.getForecast(requireContext())

        viewModel.currentForecastState.onEach {
            if (it != null) {
                Log.i("Log_tag", " currentForecastState")
                if (!ThemeProvider(requireContext().dataStore).checkMatchingThemeAndWeather(it)) {
                    activity?.recreateSmoothly()
                }
                updateCurrentWeather(it)
            }
        }.collect(lifecycle)

        viewModel.forecastForTheDaysOfTheWeek.onEach {
            if (it.isNotEmpty()) {
                Log.i("Log_tag", " forecastForTheDaysOfTheWeek")
                updateForecastForTheDay(it)
            }
        }.collect(lifecycle)

    }
}

