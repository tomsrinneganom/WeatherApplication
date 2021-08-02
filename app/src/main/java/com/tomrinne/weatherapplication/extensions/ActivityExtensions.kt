package com.tomrinne.weatherapplication.extensions

import android.app.Activity
import android.content.Intent

fun Activity.recreateSmoothly(){
   val intent =  Intent(this, this::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    startActivity(intent)
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    finish()
}