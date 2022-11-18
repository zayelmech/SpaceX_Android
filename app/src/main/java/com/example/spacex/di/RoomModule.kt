package com.example.spacex.di

import android.content.Context
import androidx.room.Room
import com.example.spacex.database.LaunchesDao
import com.example.spacex.database.LaunchesRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): LaunchesRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            LaunchesRoomDatabase::class.java,
            "spacex_database"
        ).build()
    }

    @Provides
    fun provideLaunchDao(appDatabase: LaunchesRoomDatabase): LaunchesDao {
        return appDatabase.launchesDao()
    }
}