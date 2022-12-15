package com.imecatro.data.room.di

import android.app.Application
import androidx.room.Room
import com.imecatro.data.room.LaunchesDao
import com.imecatro.data.room.LaunchesRoomDatabase
import com.imecatro.data.room.LocalDatabaseRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase( app: Application): LaunchesRoomDatabase {
        return Room.databaseBuilder(
            app,
            LaunchesRoomDatabase::class.java,
            "spacex_database"
        ).build()
    }

    @Provides
    fun provideLaunchDao(appDatabase: LaunchesRoomDatabase): LaunchesDao {
        return appDatabase.launchesDao()
    }

    @Provides
    fun provideRoomRepositoryImplementation(dao : LaunchesDao) : LocalDatabaseRepositoryImpl =
        LocalDatabaseRepositoryImpl(dao)

}