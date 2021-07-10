package com.example.weatherapplication

import android.Manifest
import android.content.Context
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var fragmentContainerView: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val theme = runBlocking {
            ThemeProvider(applicationContext.dataStore).getTheme()
        }
        setTheme(theme)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        fragmentContainerView = findViewById(R.id.fragmentContainerView)
    }

    override fun onStart() {
        super.onStart()
        checkPermission()

    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            Log.i("Log_tag", "isGranted $isGranted")
            if (!isGranted) {
                val bundle =
                    bundleOf("message" to getString(R.string.please_turn_on_geolocation))
                supportFragmentManager.commit<ErrorFragment>(R.id.fragmentContainerView, bundle)
            } else {
                supportFragmentManager.commit<ForecastFragment>(R.id.fragmentContainerView)
            }
        }

    private fun checkPermission() {

        lifecycleScope.launch {

            val locationPermission = PermissionManager().checkLocationPermission(baseContext)
            val locationDataStore = DataStoreManager(dataStore).getLastLocation()

            if (!locationPermission && locationDataStore == null) {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

            } else {
                supportFragmentManager.commit<ForecastFragment>(R.id.fragmentContainerView)
            }

        }
    }


    private inline fun <reified T : Fragment> FragmentManager.commit(
        id: Int,
        bundle: Bundle? = null,
    ) {
        if (!(fragments.isNotEmpty() && fragments.last() is T)) {
            commit {
                setReorderingAllowed(true)
                add<T>(id, args = bundle)
            }
        }
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

