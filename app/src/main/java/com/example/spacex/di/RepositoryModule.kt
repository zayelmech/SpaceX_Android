package com.example.spacex.di

import com.example.spacex.database.LocalDatabaseRepository
import com.example.spacex.database.LocalDatabaseRepositoryImpl
import com.example.spacex.network.Repository
import com.example.spacex.network.RepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRemoteRepository(
        repositoryImp: RepositoryImp
    ): Repository

    @Binds
    abstract fun provideLocalRepository(
        localDatabaseRepositoryImpl: LocalDatabaseRepositoryImpl
    ): LocalDatabaseRepository
}