package com.imecatro.domain.launches.repository

import com.imecatro.domain.launches.model.LaunchDomainModel

interface RemoteRepository {
    suspend fun getLaunches(): List<LaunchDomainModel>?
}