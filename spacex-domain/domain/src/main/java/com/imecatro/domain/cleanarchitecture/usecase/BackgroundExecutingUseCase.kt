package com.imecatro.domain.cleanarchitecture.usecase

import com.imecatro.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import kotlinx.coroutines.withContext

/**
 * This is a coroutine-specific abstract class. Using coroutines
 * It handles all  the coroutine boilerplate for us and keep clean
 * our use case implementations
 * It also hides its coroutine nature from the consumers of the use case
 */
abstract class BackgroundExecutingUseCase<REQUEST, RESULT>(
    private val coroutineContextProvider: CoroutineContextProvider
) : UseCase<REQUEST, RESULT> {


    override suspend fun execute(input: REQUEST, onResult: (RESULT) -> Unit) {
        val result = withContext(coroutineContextProvider.io) {
            executeInBackground(input)
        }
        onResult(result)
    }

    abstract fun executeInBackground(request: REQUEST): RESULT

}