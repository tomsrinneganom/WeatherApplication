package com.tomrinne.weatherapplication.enums

import android.content.res.Resources
import com.tomrinne.weatherapplication.R

enum class EnumPartOfDay {
    MORNING {
        override fun getResourceString(resources: Resources): String {
            return resources.getString(R.string.Morning)
        }
        override val index = 0
    },
    DAY {
        override fun getResourceString(resources: Resources): String {
            return resources.getString(R.string.Day)
        }
        override val index = 1
    },
    EVENING {
        override fun getResourceString(resources: Resources): String {
            return resources.getString(R.string.Evening)
        }
        override val index = 2
    },
    NIGHT {
        override fun getResourceString(resources: Resources): String {
            return resources.getString(R.string.Night)
        }
        override val index = 3
    };

    abstract fun getResourceString(resources: Resources): String
    abstract val index: Int
}