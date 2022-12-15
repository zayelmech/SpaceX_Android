package com.imecatro.domain.launches.usecases

import com.imecatro.domain.launches.repository.LocalRepository
import com.imecatro.domain.launches.repository.RemoteRepository

class UpdateAllLaunchesUseCase(
    private val remoteRepository: RemoteRepository,
    private val localDatabaseRepository: LocalRepository,
) {

    suspend operator fun invoke(): Throwable? {

        try {
            val response = remoteRepository.getLaunches()
            response?.let { launchesEntities ->
                localDatabaseRepository.updateAllLaunches(launchesEntities)
            } ?: return Throwable("Error null")
            //Log.e(TAG, "ERROR 404 " + response.message())

        } catch (e: Exception) {
            return Throwable(e.message)
            //Log.e(TAG, "ERROR UNABLE TO CONNECT " + e.message)
        }
        return null
    }


}


