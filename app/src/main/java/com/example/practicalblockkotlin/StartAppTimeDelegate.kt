package com.example.practicalblockkotlin

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

class StartAppTimeDelegate {

    private val startTime = System.currentTimeMillis()
    private var isLogging = false

    @OptIn(DelicateCoroutinesApi::class)
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Long {
        isLogging = true
        GlobalScope.launch(Dispatchers.IO) {
            while (isLogging) {
                println(startTime)
                delay(3000)
            }
        }
        return startTime
    }
}