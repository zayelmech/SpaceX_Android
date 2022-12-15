package com.imecatro.domain.cleanarchitecture.usecase

/**This UseCase interface is used to declare a contract between the UseCaseExecutor and
 * all use cases
 */
interface UseCase<REQUEST, RESULT> {
    suspend fun execute(input : REQUEST, onResult: (RESULT) -> Unit)
}