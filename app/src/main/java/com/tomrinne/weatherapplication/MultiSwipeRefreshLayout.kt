package com.tomrinne.weatherapplication

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlin.math.abs

class MultiSwipeRefreshLayout : SwipeRefreshLayout {
    private val touchSlop: Int
    private var mPrevX: Float? = null

    constructor(context: Context) : super(context) {
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> mPrevX = MotionEvent.obtain(event).x
            MotionEvent.ACTION_MOVE -> {
                if (mPrevX != null) {
                    val eventX: Float = event.x
                    val xDiff: Float = abs(eventX - mPrevX!!)
                    if (xDiff > touchSlop) {
                        return false
                    }
                }
            }
        }

        return super.onInterceptTouchEvent(event)
    }
}