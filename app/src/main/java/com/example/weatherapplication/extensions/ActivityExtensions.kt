package com.example.weatherapplication.extensions

import android.app.Activity
import android.content.Intent

fun Activity.recreateSmoothly(){
    startActivity(Intent(this, this::class.java))
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    finish()
}