package com.example.spacex.usecases

import com.example.spacex.database.LaunchRoomEntity
import com.example.spacex.database.LocalDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllLaunchesUseCase @Inject constructor(
    private val localDatabaseRepository: LocalDatabaseRepository
) {

    operator fun invoke(): Flow<List<LaunchRoomEntity>> =
        localDatabaseRepository.getLaunches()


}

