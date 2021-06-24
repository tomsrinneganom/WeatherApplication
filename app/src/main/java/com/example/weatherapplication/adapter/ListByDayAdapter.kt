package com.example.weatherapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.data.ForecastByPartsOfTheDay
import com.example.weatherapplication.data.ForecastForTheDay
import com.example.weatherapplication.utils.ForecastUtils


class ListByDayAdapter(private val forecastList: List<ForecastForTheDay>) :
    RecyclerView.Adapter<ListByDayAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(forecast: ForecastForTheDay) {
            val utils = ForecastUtils()
            itemView.findViewById<TextView>(R.id.dateTextView).text =
                forecast.getDate(itemView.resources)

            itemView.findViewById<TextView>(R.id.degreesCelsiusTextView).text =
                forecast.getTemperature(itemView.resources)

            itemView.findViewById<ImageView>(R.id.weatherIconsImageView).setImageDrawable(
                forecast.getWeatherIcon(itemView.context)
            )


            val recyclerView = itemView.findViewById<RecyclerView>(R.id.recyclerView)

            val adapter = ForecastByPartsOfTheDayAdapter(forecast.hourlyForecast)

            recyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)

            recyclerView.adapter = adapter
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.weather_list_by_day, parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.i("Log_Tag", "list size")
        holder.bind(forecastList[position])
    }

    override fun getItemCount() = forecastList.size
}