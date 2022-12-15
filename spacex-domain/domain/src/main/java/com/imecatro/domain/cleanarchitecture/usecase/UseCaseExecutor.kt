package com.imecatro.domain.cleanarchitecture.usecase

import com.imecatro.domain.cleanarchitecture.exception.DomainException
import com.imecatro.domain.cleanarchitecture.exception.UnknownDomainException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * To avoid exposing every ViewModel to the coroutine dependency, as well as to
 * provide a cancel mechanism, using this, is a good idea
 * As the name suggest, a @UseCaseCaseExecutor executes UseCases for us.
 * It encapsulates the execution details of the UseCases and can provide us
 * with a mechanism to cancel the execution of a UseCase
 */
class UseCaseExecutor(
    private val coroutineScope: CoroutineScope
) {
    fun <INPUT, OUTPUT> execute(
        useCase: UseCase<INPUT, OUTPUT>,
        value: INPUT,
        onResult: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ) {
        coroutineScope.launch {
            try {
                useCase.execute(value, onResult)
            } catch (ignore: CancellationException) {

            } catch (throwable: Throwable) {
                onException(
                    (throwable as? DomainException)
                        ?: UnknownDomainException(throwable)
                )
            }
        }

    }

}