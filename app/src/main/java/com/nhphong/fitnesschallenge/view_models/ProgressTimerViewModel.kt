package com.nhphong.fitnesschallenge.view_models

import android.databinding.ObservableField
import android.os.Handler
import java.text.SimpleDateFormat
import java.util.*

class ProgressTimerViewModel {
    companion object {
        private const val MAX_TIME_MILLIS = 120000L
        private const val INTERVAL = 1000L
    }

    val timeObs = ObservableField<String>()
    private val dateFormat = SimpleDateFormat("mm:ss", Locale.US)
    private var handler: Handler? = null
    private var currentTimeInMillis = 0L
    private val runnable = object: Runnable {
        override fun run() {
            if (currentTimeInMillis + INTERVAL <= MAX_TIME_MILLIS) {
                setCurrentTime(currentTimeInMillis + INTERVAL)
                handler?.postDelayed(this, INTERVAL)
            }
        }
    }

    init {
        handler = Handler()
    }

    fun startTimer() {
        setCurrentTime(0L)
        handler?.postDelayed(runnable, INTERVAL)
    }

    fun stopTimer() {
        handler?.removeCallbacks(null)
    }

    fun recycle() {
        handler?.removeCallbacks(null)
        handler = null
    }

    private fun setCurrentTime(currentTime: Long) {
        currentTimeInMillis = currentTime
        timeObs.set(dateFormat.format(Date(currentTimeInMillis)))
    }
}
