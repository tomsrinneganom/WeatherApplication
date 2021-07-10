package com.example.weatherapplication

import java.lang.Exception

abstract class Result<T>(
    val value: T? = null,
    val source: Int = UNKNOWN_SOURCE,
    val exception: Exception? = null,
    val isLoading: Boolean = true,
) {
    fun isSuccess(): Boolean {
        return value != null
    }

    companion object {
        const val UNKNOWN_SOURCE = 0
        const val DATASTORE_MANAGER_SOURCE = 1
    }
}