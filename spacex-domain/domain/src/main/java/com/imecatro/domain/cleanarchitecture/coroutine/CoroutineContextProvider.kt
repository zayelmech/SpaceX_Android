package com.imecatro.domain.cleanarchitecture.coroutine

import kotlin.coroutines.CoroutineContext

/**
 * Since we are working with coroutine-specific background
 * our use case requires a coroutine context
 */
interface CoroutineContextProvider {
    val main : CoroutineContext
    val io : CoroutineContext
}