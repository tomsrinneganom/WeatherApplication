package com.example.weatherapplication.enums

import android.content.res.Resources
import com.example.weatherapplication.R

enum class PartOfDay {
    MORNING {
        override fun getResourceString(resources: Resources): String {
            return resources.getString(R.string.Morning)
        }
    },
    DAY {
        override fun getResourceString(resources: Resources): String {
            return resources.getString(R.string.Day)
        }
    },
    EVENING {
        override fun getResourceString(resources: Resources): String {
            return resources.getString(R.string.Evening)
        }
    },
    NIGHT {
        override fun getResourceString(resources: Resources): String {
            return resources.getString(R.string.Night)
        }
    };

    abstract fun getResourceString(resources: Resources): String
}