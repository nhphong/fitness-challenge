package com.nhphong.fitnesschallenge.view_models

import android.databinding.ObservableField
import android.os.Handler
import io.reactivex.subjects.BehaviorSubject
import java.text.SimpleDateFormat
import java.util.*

class ProgressTimerViewModel {

    val timeObs = ObservableField<String>()
    val progressObs: BehaviorSubject<Float> = BehaviorSubject.create<Float>()
    var interval = 1000L
    var maxTimeMillis = 30000L

    private val dateFormat = SimpleDateFormat("mm:ss", Locale.US)
    private var handler: Handler? = null
    private var currentTimeInMillis = 0L
    private val runnable = object: Runnable {
        override fun run() {
            if (currentTimeInMillis - interval >= 0) {
                setCurrentTime(currentTimeInMillis - interval)
                handler?.postDelayed(this, interval)
            }
        }
    }

    init {
        handler = Handler()
    }

    fun startTimer() {
        setCurrentTime(maxTimeMillis)
        handler?.postDelayed(runnable, interval)
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
        progressObs.onNext(currentTime.toFloat() / maxTimeMillis)
    }
}
