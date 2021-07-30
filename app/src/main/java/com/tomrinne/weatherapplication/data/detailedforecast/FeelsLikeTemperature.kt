package com.tomrinne.weatherapplication.data.detailedforecast

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.tomrinne.weatherapplication.R

class FeelsLikeTemperature(val value: Int) : DetailForecast {


    override fun getValueToDisplay(resources: Resources): String {
        return resources.getString(R.string.temperature, value)
    }

    override fun getDescriptionToDisplay(resources: Resources): String {
        return resources.getString(R.string.feels_like_temperature)
    }

    override fun getIcon(context: Context): Drawable? {
        return ContextCompat.getDrawable(context, R.drawable.ic_feels_like_temperature)
    }
}