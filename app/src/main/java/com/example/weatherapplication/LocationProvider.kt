package com.example.weatherapplication

import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.flow.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationProvider {

    private var cancellationTokenSource = CancellationTokenSource()
    suspend fun getCurrentLocation(
        context: Context,
    ): Flow<Location?> {
        val dataStoreManager = DataStoreManager(context.dataStore)
        val flow = MutableStateFlow(dataStoreManager.getLastLocation())

        if (PermissionManager().checkLocationPermission(context)) {

            val locationProviderClient =
                LocationServices.getFusedLocationProviderClient(context)

            val currentLocationTask =
                locationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY,
                    cancellationTokenSource.token)
            currentLocationTask.addOnCompleteListener {
                if (it.isSuccessful) {
                    flow.value = it.result
                } else {
                    Log.i("Log_tag", "exception ${it.exception}")
                    flow.value = null
                }
            }

        } else
            flow.value = dataStoreManager.getLastLocation()

        return flow.map {
            if(it != null){
                dataStoreManager.insertLastLocation(it)
            }
            it ?: dataStoreManager.getLastLocation()
        }

    }

    private fun getLocality(context: Context, location: Location): String {
        val address =
            Geocoder(context).getFromLocation(location.latitude, location.longitude, 1)
        var locality = ""
        address.forEach {
            locality = it.locality
        }
        return locality
    }

    suspend fun getLocality(context: Context): Flow<String> {
        return flow {
            getCurrentLocation(context).collect { location ->
                if (location != null) {
                    emit(getLocality(context, location))
                }
            }
        }
    }

}