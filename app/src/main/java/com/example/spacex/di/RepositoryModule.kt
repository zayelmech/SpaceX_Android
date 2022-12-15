package com.example.spacex.di

import com.imecatro.data.room.LocalDatabaseRepositoryImpl
import com.imecatro.data.network.RepositoryImp
import com.imecatro.domain.launches.repository.LocalRepository
import com.imecatro.domain.launches.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRemoteRepository(
        repositoryImp: RepositoryImp
    ): RemoteRepository

    @Binds
    abstract fun provideLocalRepository(
        localDatabaseRepositoryImpl: LocalDatabaseRepositoryImpl
    ): LocalRepository
}