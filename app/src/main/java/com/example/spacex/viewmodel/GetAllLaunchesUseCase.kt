package com.example.spacex.viewmodel

import com.example.spacex.model.LaunchesEntity
import com.example.spacex.network.Repository
import javax.inject.Inject

class GetAllLaunchesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun invoke(): List<LaunchesEntity>?{
        var allLaunches : List<LaunchesEntity>? = null

        try {
            val response = repository.getLaunches()
            response.body()?.let {
                allLaunches = it
            } ?: throw Throwable(response.message())
        } catch (e: Exception) {
            throw Throwable(e)
        }
        return allLaunches
    }
}