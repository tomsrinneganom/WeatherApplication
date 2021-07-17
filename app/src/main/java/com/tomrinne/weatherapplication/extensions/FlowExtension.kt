package com.tomrinne.weatherapplication.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> Flow<T>.collect(lifecycleScope: Lifecycle) {
    lifecycleScope.coroutineScope.launch {
        lifecycleScope.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@collect.collect()
        }
    }
}