package com.example.spacex.di

import android.app.Application
import com.imecatro.data.network.di.NetworkModule
import com.imecatro.data.room.LaunchesRoomDatabase
import com.imecatro.data.room.di.RoomModule
import com.imecatro.domain.launches.repository.LocalRepository
import com.imecatro.domain.launches.repository.RemoteRepository
import com.imecatro.domain.launches.usecases.GetAllLaunchesUseCase
import com.imecatro.domain.launches.usecases.UpdateAllLaunchesUseCase
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.currentCoroutineContext
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun getAllLaunchesUseCase(
        localDatabaseRepository: LocalRepository
    ): GetAllLaunchesUseCase {
        return GetAllLaunchesUseCase(localDatabaseRepository)
    }

    @Provides
    fun updateAllLaunchesUseCase(
        remoteRepository: RemoteRepository,
        localDatabaseRepository: LocalRepository
    ): UpdateAllLaunchesUseCase {
        return UpdateAllLaunchesUseCase(
            remoteRepository,
            localDatabaseRepository
        )
    }

}

//@Singleton
//@Component(modules = [RoomModule::class,NetworkModule::class])
//interface SpacexAppComponent{
//
//    fun inject(module: RoomModule)
//    fun inject(module: NetworkModule)
//
//}
