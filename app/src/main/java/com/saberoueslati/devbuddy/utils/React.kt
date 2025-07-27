package com.saberoueslati.devbuddy.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Collects emissions from a [Flow] inside a [Composable] using [LaunchedEffect],
 * and runs the specified [block] for each emitted value.
 *
 * This is a convenient way to perform side effects in Compose (e.g., navigation, showing snackbars).
 *
 * @param context Optional [CoroutineContext] to apply with [flowOn]. Defaults to [EmptyCoroutineContext].
 * @param block The action to perform for each emitted value.
 */
@Composable
fun <T> Flow<T>.React(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend (T) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        onEach(block).flowOn(context).launchIn(this)
    }
}