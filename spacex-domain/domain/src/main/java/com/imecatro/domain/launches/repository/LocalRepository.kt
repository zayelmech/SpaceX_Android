package com.imecatro.domain.launches.repository

import com.imecatro.domain.launches.model.LaunchDomainModel
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun getLaunches(): Flow<List<LaunchDomainModel>>
    suspend fun insertLaunch(launchRoomEntity: LaunchDomainModel)
    suspend fun updateAllLaunches(launches: List<LaunchDomainModel>)
}