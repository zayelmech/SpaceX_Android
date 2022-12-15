package com.imecatro.data.network

import com.imecatro.domain.launches.model.LaunchDomainModel
import com.imecatro.domain.launches.repository.RemoteRepository
import com.imecatro.data.network.model.LaunchesEntity
import javax.inject.Inject

class RepositoryImp(
    private val spacexService: SpacexService
) :RemoteRepository {

    override suspend fun getLaunches(): List<LaunchDomainModel>? =
        spacexService.getLaunches().body()?.toDomain()

}


