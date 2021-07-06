package com.example.weatherapplication

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationProvider {
    suspend fun getCurrentLocation(context: Context) = suspendCoroutine<Location?> {
        val fusedLocationProvider = LocationServices.getFusedLocationProviderClient(context)
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            it.resume(null)
        } else {
            fusedLocationProvider.lastLocation.addOnSuccessListener { location ->
                it.resume(location)
            }.addOnFailureListener { exception ->
                Log.i("Log_tag", "exception")
                it.resume(null)
            }
        }
    }

    fun getLocalityName(context: Context, location: Location): String {
        val address =
            Geocoder(context).getFromLocation(location.latitude, location.longitude, 1)
        Log.i("Log_tag", "address size:${address.size}")
        var locality = ""
        address.forEach {
            Log.i("Log_tag", "it.featureName ${it.locality}")
            locality = it.locality
        }
        return locality
    }

    suspend fun getLocalityName(context: Context): String {
        val location = getCurrentLocation(context)
        return if (location != null) {
            getLocalityName(context, location)
        } else {
            ""
        }
    }
}