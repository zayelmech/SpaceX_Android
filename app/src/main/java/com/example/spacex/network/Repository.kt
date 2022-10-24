package com.example.spacex.network

import com.example.spacex.model.LaunchesEntity
import retrofit2.Response
import javax.inject.Inject


interface Repository {
    suspend fun getLaunches(): Response<List<LaunchesEntity>>
}

class RepositoryImp @Inject constructor(
    private val spacexService: SpacexService
) : Repository {

    override suspend fun getLaunches(): Response<List<LaunchesEntity>> =
        spacexService.getLaunches()

}