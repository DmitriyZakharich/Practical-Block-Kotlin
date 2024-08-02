package com.example.practicalblockkotlin

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StartAppTimeDelegate : ReadWriteProperty<Any?, Boolean> {
    private var job: Job? = null
    private val startTime = System.currentTimeMillis()


    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return job?.isActive ?: false
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        if (value) {
            startLogging()
        } else {
            stopLogging()
        }
    }

    private fun startLogging() {
        if (job == null) {
            job = CoroutineScope(Dispatchers.IO).launch {
                while (isActive) {
                    delay(3000)
                    Log.d(TAG, "Время запуска: $startTime")
                }
            }
        }
    }

    private fun stopLogging() {
        job?.cancel()
        job = null
    }
}