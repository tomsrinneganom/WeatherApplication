package com.tomrinne.weatherapplication.data.detailedforecast

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.tomrinne.weatherapplication.R


class Humidity(
     val value: Int,
) : DetailForecast {

    override fun getValueToDisplay(resources: Resources): String {
        return "$value%"
    }

    override fun getDescriptionToDisplay(resources: Resources): String {
        return resources.getString(R.string.humidity)
    }

    override fun getIcon(context: Context): Drawable? {
        return ContextCompat.getDrawable(context, R.drawable.ic_humidity)
    }
}