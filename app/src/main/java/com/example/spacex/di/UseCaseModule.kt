package com.example.spacex.di

import com.example.spacex.network.Repository
import com.example.spacex.viewmodel.GetAllLaunchesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun getAllLaunchesUseCase(repository: Repository) =
        GetAllLaunchesUseCase(repository)

}