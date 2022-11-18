package com.example.spacex.database

import androidx.annotation.WorkerThread
import javax.inject.Inject


interface LocalDatabaseRepository {
    suspend fun getLaunches(): List<LaunchRoomEntity>
    suspend fun insert(launchRoomEntity: LaunchRoomEntity)
    suspend fun updateLaunches(launches: List<LaunchRoomEntity>)
}


class LocalDatabaseRepositoryImpl @Inject constructor(
    private val launchesDao: LaunchesDao
) : LocalDatabaseRepository {


    override suspend fun getLaunches(): List<LaunchRoomEntity> {

//        val remoteList = spacexService.getLaunches()
//        if (remoteList.isSuccessful) {
//            remoteList.body()?.forEach { launchesEntity ->
//                localDatabaseDao.insertNewLaunch(launchesEntity.toRoomEntity())
//            }
//
//        }

        return launchesDao.getAllLaunches()
    }

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