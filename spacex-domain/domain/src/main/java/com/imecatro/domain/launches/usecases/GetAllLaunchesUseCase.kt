package com.imecatro.domain.launches.usecases

import com.imecatro.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import com.imecatro.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase
import com.imecatro.domain.launches.model.LaunchDomainModel
import com.imecatro.domain.launches.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class GetAllLaunchesUseCase(
    private val localDatabaseRepository: LocalRepository
   // coroutineContextProvider: CoroutineContextProvider
)
//    : BackgroundExecutingUseCase<String, Flow<List<LaunchDomainModel>>>(coroutineContextProvider)
{

    operator fun invoke(): Flow<List<LaunchDomainModel>> =
        localDatabaseRepository.getLaunches()

//    override fun executeInBackground(request: String): Flow<List<LaunchDomainModel>> {
//        return localDatabaseRepository.getLaunches()
//    }

}

