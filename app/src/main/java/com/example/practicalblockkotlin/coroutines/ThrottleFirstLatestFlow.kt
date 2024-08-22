package com.example.practicalblockkotlin.coroutines

import android.util.Log
import com.example.practicalblockkotlin.TAG2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

fun startThrottleFirst() {
    CoroutineScope(Job()).launch {
        val myFlow = flow {
            emit(1)
            delay(200)
            emit(2)
            emit(3)
            delay(550)
            emit(4)
            emit(5)
            delay(500)
            emit(6)
            emit(7)
        }

        myFlow.throttleFirst(500)
            .collect {
                Log.d(TAG2, "flow1: $it")
            }

        myFlow.throttleLatest(500)
            .collect {
                Log.d(TAG2, "flow2: $it")
            }
    }
}

fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> {
    if (windowDuration < 0) throw RuntimeException("Wrong window duration")

    return flow {
        var lastEmitTime = 0L
        collect { value ->
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastEmitTime >= windowDuration) {
                lastEmitTime = currentTime
                emit(value)
            }
        }
    }
}

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
fun <T> Flow<T>.throttleLatest(windowDuration: Long): Flow<T> =
    this.debounce(windowDuration)
        .mapLatest { it }


val hotFlow = MutableSharedFlow<Int>()
val coldFlow = flow {
    hotFlow.collect { emit(it) }
}