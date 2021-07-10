package com.example.weatherapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.adapter.ForecastByPartsOfTheDayAdapter.ForecastByPartsOfTheDayHolder
import com.example.weatherapplication.data.ForecastByPartsOfTheDay
import com.example.weatherapplication.enums.EnumPartOfDay

class ForecastByPartsOfTheDayAdapter(forecastList: List<ForecastByPartsOfTheDay>) :
    RecyclerView.Adapter<ForecastByPartsOfTheDayHolder>() {

    private val forecastList: MutableList<ForecastByPartsOfTheDay> = mutableListOf()

    init {
        this.forecastList.addAndSort(forecastList)
    }

    class ForecastByPartsOfTheDayHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(forecast: ForecastByPartsOfTheDay) {
            itemView.findViewById<ImageView>(R.id.weatherIconsImageView).isVisible = false

            itemView.findViewById<TextView>(R.id.timeOfDayTextView).text =
                forecast.getPartOfTheDay(itemView.resources)

            itemView.findViewById<TextView>(R.id.degreesCelsiusTextView).text =
                forecast.getTemperature(itemView.resources)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ForecastByPartsOfTheDayHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_list_by_time, parent, false)
        )

    override fun onBindViewHolder(holder: ForecastByPartsOfTheDayHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount() = forecastList.size

    fun updateList(newForecastList: List<ForecastByPartsOfTheDay>) {
        forecastList.clear()
        forecastList.addAndSort(newForecastList)
        notifyDataSetChanged()
    }

}

fun MutableList<ForecastByPartsOfTheDay>.addAndSort(list: List<ForecastByPartsOfTheDay>) {
    this.addAll(list.sortedBy {
        EnumPartOfDay.valueOf(it.partOfTheDay).index
    })
}