package com.example.weatherapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.data.ForecastForDaysOfTheWeek


class ListByDayAdapter(private val forecastList: List<ForecastForDaysOfTheWeek>) :
    RecyclerView.Adapter<ListByDayAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(forecast: ForecastForDaysOfTheWeek) {

            itemView.findViewById<TextView>(R.id.dateTextView).text =
                forecast.forecastForTheDay.getDate(itemView.resources)

            itemView.findViewById<TextView>(R.id.degreesCelsiusTextView).text =
                forecast.forecastForTheDay.getTemperature(itemView.resources)

            itemView.findViewById<ImageView>(R.id.weatherIconsImageView).setImageDrawable(
                forecast.forecastForTheDay.getDrawableWeatherIcon(itemView.context)
            )

            val recyclerView = itemView.findViewById<RecyclerView>(R.id.recyclerView)

            val adapter = ForecastByPartsOfTheDayAdapter(forecast.forecastByPartsOfTheDay)

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