package com.example.spacex.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface LocalDatabaseRepository {
    fun getLaunches(): Flow<List<LaunchRoomEntity>>
    suspend fun insert(launchRoomEntity: LaunchRoomEntity)
    suspend fun updateLaunches(launches: List<LaunchRoomEntity>)
}


class LocalDatabaseRepositoryImpl @Inject constructor(
    private val launchesDao: LaunchesDao
) : LocalDatabaseRepository {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.

    override fun getLaunches(): Flow<List<LaunchRoomEntity>> = launchesDao.getAllLaunches()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insert(launchRoomEntity: LaunchRoomEntity) {
        launchesDao.insertNewLaunch(launchRoomEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun updateLaunches(launches: List<LaunchRoomEntity>) {
        launchesDao.updateLaunches(launches)
    }

}