package com.tomrinne.weatherapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tomrinne.weatherapplication.R
import com.tomrinne.weatherapplication.data.detailedforecast.DetailForecast

class DetailedForecastAdapter(detailForecast: List<DetailForecast> = emptyList()) :
    RecyclerView.Adapter<DetailedForecastAdapter.DetailedForecastAdapterHolder>() {

    private val detailedForecast = mutableListOf<DetailForecast>()

    init {
        this.detailedForecast.addAll(detailForecast)
    }

    class DetailedForecastAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: DetailForecast) {

            itemView.findViewById<TextView>(R.id.detailValueTextView).text =
                item.getValueToDisplay(itemView.resources)
            itemView.findViewById<TextView>(R.id.detailDescriptionTextView).text =
                item.getDescriptionToDisplay(itemView.resources)
            itemView.findViewById<ImageView>(R.id.detailIconImageView)
                .setImageDrawable(item.getIcon(itemView.context))

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DetailedForecastAdapterHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.detailed_forecast_item, parent, false)
        )

    override fun onBindViewHolder(holder: DetailedForecastAdapterHolder, position: Int) {
        holder.bind(detailedForecast[position])
    }

    override fun getItemCount() = detailedForecast.size

    fun update(detailForecast: List<DetailForecast>) {
        this.detailedForecast.clear()
        this.detailedForecast.addAll(detailForecast)
    }
}