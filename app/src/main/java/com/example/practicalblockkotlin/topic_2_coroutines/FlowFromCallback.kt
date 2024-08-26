package com.example.practicalblockkotlin.topic_2_coroutines

import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.cancellation.CancellationException

/**Необходимо сконвертировать функцию с колбэком в flow. Сделать средствами kotlin.x coroutines
fun flowFrom(api: CallbackBasedApi): Flow<T> {
    val callback = object : Callback {
        override fun onNextValue(value: T) {}
        override fun onApiError(cause: Throwable) {}
        override fun onCompleted() {}
    }
}
 */

fun <T> flowFrom(api: CallbackBasedApi): Flow<T> = callbackFlow {
    val callback = object : Callback {
        override fun onNextValue(value: T) {
            trySendBlocking(value)
                .onFailure { throwable ->
                }
        }

        override fun onApiError(cause: Throwable) {
            cancel(CancellationException("API Error", cause))
        }

        override fun onCompleted() = channel.close()
    }
    api.register(callback)
    awaitClose { api.unregister(callback) }
}