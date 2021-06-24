package com.example.weatherapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.adapter.ForecastByPartsOfTheDayAdapter.ForecastByPartsOfTheDayHolder
import com.example.weatherapplication.data.ForecastByPartsOfTheDay
import com.example.weatherapplication.data.HourlyForecast

class ForecastByPartsOfTheDayAdapter(private val forecastList: List<ForecastByPartsOfTheDay>) :
    RecyclerView.Adapter<ForecastByPartsOfTheDayHolder>() {

    class ForecastByPartsOfTheDayHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(forecast: ForecastByPartsOfTheDay) {
            itemView.findViewById<TextView>(R.id.timeOfDayTextView).text =
                forecast.getTime(itemView.resources)
            itemView.findViewById<TextView>(R.id.degreesCelsiusTextView).text =
                forecast.getTemperature(itemView.resources)
            itemView.findViewById<ImageView>(R.id.weatherIconsImageView).visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ForecastByPartsOfTheDayHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.weather_list_by_time, parent, false)
    )

    override fun onBindViewHolder(holder: ForecastByPartsOfTheDayHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount() = forecastList.size
}