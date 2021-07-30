package com.tomrinne.weatherapplication.data.detailedforecast

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import com.google.gson.Gson

interface DetailForecast {
    fun getValueToDisplay(resources: Resources): String
    fun getDescriptionToDisplay(resources: Resources): String
    fun getIcon(context: Context): Drawable?
}