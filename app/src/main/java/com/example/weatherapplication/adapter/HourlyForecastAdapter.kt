package com.example.weatherapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.data.HourlyForecast

class HourlyForecastAdapter(forecastList: List<HourlyForecast> = emptyList()) :
    RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastHolder>() {

    private val forecastList = mutableListOf<HourlyForecast>()

    init {
        this.forecastList.addAll(forecastList)
    }

    class HourlyForecastHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(forecast: HourlyForecast) {
            itemView.findViewById<TextView>(R.id.timeOfDayTextView).text =
                forecast.getHourAndMinute()

            itemView.findViewById<TextView>(R.id.degreesCelsiusTextView).text =
                forecast.getTemperature(itemView.resources)

            itemView.findViewById<ImageView>(R.id.weatherIconsImageView).setImageDrawable(
                forecast.getDrawableWeatherIcon(itemView.context)
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HourlyForecastHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.weather_list_by_time, parent, false)
    )

    override fun onBindViewHolder(holder: HourlyForecastHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    fun updateList(newForecastList: List<HourlyForecast>) {
        forecastList.clear()
        forecastList.addAll(newForecastList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = forecastList.size
}