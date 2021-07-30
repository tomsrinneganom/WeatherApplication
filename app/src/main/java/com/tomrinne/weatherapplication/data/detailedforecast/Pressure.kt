package com.tomrinne.weatherapplication.data.detailedforecast

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.tomrinne.weatherapplication.R

class Pressure( val value: Long) : DetailForecast {

    override fun getValueToDisplay(resources: Resources): String {
        return "${value / 1000} ${resources.getString(R.string.bar)}"
    }

    override fun getDescriptionToDisplay(resources: Resources): String {
        return resources.getString(R.string.pressure)
    }

    override fun getIcon(context: Context): Drawable? {
        return ContextCompat.getDrawable(context, R.drawable.ic_pressure)
    }
}
