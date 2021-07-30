package com.tomrinne.weatherapplication.data.detailedforecast

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.tomrinne.weatherapplication.R


class WindSpeed(val value: Double) : DetailForecast {

    override fun getValueToDisplay(resources: Resources): String {
        return "${value.toInt()} ${resources.getString(R.string.kilometers_per_hour)}"
    }

    override fun getDescriptionToDisplay(resources: Resources): String {
        return resources.getString(R.string.wind_speed)
    }

    override fun getIcon(context: Context): Drawable? {
        return ContextCompat.getDrawable(context, R.drawable.ic_wind)
    }
}