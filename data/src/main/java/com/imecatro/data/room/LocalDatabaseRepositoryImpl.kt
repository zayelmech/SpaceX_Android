package com.imecatro.data.room

import androidx.annotation.WorkerThread
import com.imecatro.data.room.model.LaunchRoomEntity
import com.imecatro.domain.launches.model.LaunchDomainModel
import com.imecatro.domain.launches.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class LocalDatabaseRepositoryImpl(
    private val launchesDao: LaunchesDao
) : LocalRepository {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.

    override fun getLaunches(): Flow<List<LaunchDomainModel>> {
        return flow {
            launchesDao.getAllLaunches().collect { list ->
                emit(list.toDomain())
            }
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertLaunch(launchDomainEntity: LaunchDomainModel) {
        launchesDao.insertNewLaunch(launchDomainEntity.toData())
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun updateAllLaunches(launches: List<LaunchDomainModel>) {
        launchesDao.updateLaunches(launches.toData())
    }

}

