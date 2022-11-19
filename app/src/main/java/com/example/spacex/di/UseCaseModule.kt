package com.example.spacex.di

import com.example.spacex.database.LocalDatabaseRepository
import com.example.spacex.network.Repository
import com.example.spacex.usecases.GetAllLaunchesUseCase
import com.example.spacex.usecases.UpdateAllLaunchesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun getAllLaunchesUseCase(
        localDatabaseRepository: LocalDatabaseRepository
    ): GetAllLaunchesUseCase {
        return GetAllLaunchesUseCase(localDatabaseRepository)
    }

    @Provides
    fun updateAllLaunchesUseCase(
        remoteRepository: Repository,
        localDatabaseRepository: LocalDatabaseRepository
    ): UpdateAllLaunchesUseCase {
        return UpdateAllLaunchesUseCase(remoteRepository, localDatabaseRepository)
    }
}