package com.example.practicalblockkotlin.topic_2_coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest

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
