package com.example.weatherapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.data.HourlyForecast

class HourlyForecastAdapter(private val forecastList: List<HourlyForecast>) :
    RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastHolder>() {

    class HourlyForecastHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(forecast: HourlyForecast) {

            itemView.findViewById<TextView>(R.id.timeOfDayTextView).text =
                forecast.getTime(itemView.resources)

            itemView.findViewById<TextView>(R.id.degreesCelsiusTextView).text =
                forecast.getTemperature(itemView.resources)

            itemView.findViewById<ImageView>(R.id.weatherIconsImageView).setImageDrawable(
                forecast.getWeatherIcon(itemView.context)
            )

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HourlyForecastHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.weather_list_by_time, parent, false)
    )

    override fun onBindViewHolder(holder: HourlyForecastHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount() = forecastList.size
}