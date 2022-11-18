package com.example.spacex.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LaunchRoomEntity::class], version = 1)
abstract class LaunchesRoomDatabase : RoomDatabase() {
    abstract fun launchesDao(): LaunchesDao
}