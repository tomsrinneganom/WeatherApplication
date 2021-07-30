package com.tomrinne.weatherapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tomrinne.weatherapplication.R
import com.tomrinne.weatherapplication.data.detailedforecast.DetailForecast

class DetailedForecastForDaysOfTheWeekAdapter(detailForecast: List<DetailForecast> = emptyList()) :
    RecyclerView.Adapter<DetailedForecastForDaysOfTheWeekAdapter.DetailedForecastForDaysOfTheWeekHolder>() {

    private val detailedForecast = mutableListOf<DetailForecast>()

    init {
        this.detailedForecast.addAll(detailForecast)
    }

    class DetailedForecastForDaysOfTheWeekHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(item: DetailForecast) {
            itemView.findViewById<TextView>(R.id.detailValueTextView).text =
                item.getValueToDisplay(itemView.resources)

            itemView.findViewById<ImageView>(R.id.detailIconImageView)
                .setImageDrawable(item.getIcon(itemView.context))

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DetailedForecastForDaysOfTheWeekHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.mini_detailed_forecast_item, parent, false)
        )

    override fun onBindViewHolder(holder: DetailedForecastForDaysOfTheWeekHolder, position: Int) {
        holder.bind(detailedForecast[position])
    }

    override fun getItemCount() = detailedForecast.size

}