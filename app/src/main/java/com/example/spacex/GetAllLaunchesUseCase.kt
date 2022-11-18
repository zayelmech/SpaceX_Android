package com.example.spacex

import android.util.Log
import com.example.spacex.database.LaunchRoomEntity
import com.example.spacex.database.LocalDatabaseRepository
import com.example.spacex.model.LaunchesEntity
import com.example.spacex.network.Repository
import javax.inject.Inject

class GetAllLaunchesUseCase @Inject constructor(
    private val remoteRepository: Repository,
    private val localDatabaseRepository: LocalDatabaseRepository
) {
    suspend operator fun invoke(): List<LaunchRoomEntity>? {

//        var allLaunches: List<LaunchesEntity>? = null

        try {
            val response = remoteRepository.getLaunches()
            response.body()?.let { launchesEntities ->
                localDatabaseRepository.updateLaunches(launchesEntities.toRoomEntity())
//                    launchesEntities.forEach { launch ->
//                        localDatabaseRepository.insert(launch.toRoomEntity())
//                    }
                //allLaunches = it
            } ?: Log.e(TAG, "ERROR 404 " + response.message())

        } catch (e: Exception) {
            //return Throwable("ERROR UNABLE TO CONNECT" + e)
            Log.e(TAG, "ERROR UNABLE TO CONNECT " + e.message)
        }
        return localDatabaseRepository.getLaunches()
    }


    companion object {
        const val TAG = "GetAllLaunchesUseCase"

        fun List<LaunchesEntity>.toRoomEntity(): List<LaunchRoomEntity> {
            return map { launchesEntity ->
                LaunchRoomEntity(
                    missionName = launchesEntity.missionName,
                    rocketName = launchesEntity.rocket.rocketName,
                    dateOfLaunch = launchesEntity.launchDateUtc,
                    imageUrl = launchesEntity.links.missionPatchSmall,
                    launchNumber = launchesEntity.flightNumber,
                    site = launchesEntity.launchSite.siteName,
                    success = launchesEntity.launchSuccess?: false,
                    year = launchesEntity.launchYear?: "2000",
                    details = launchesEntity.details ?: "N/A",
                    website = launchesEntity.links.articleLink?: "spacex.com"
                )
            }
        }

    }
}


