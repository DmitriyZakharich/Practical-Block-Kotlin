package com.example.practicalblockkotlin.topic_2_coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform

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

fun <T> Flow<T>.throttleLatest(windowDuration: Long): Flow<T> {
    return this.conflate()
        .transform {
            emit(it)
            delay(windowDuration)
        }
}
