package com.imecatro.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imecatro.data.room.model.LaunchRoomEntity

@Database(entities = [LaunchRoomEntity::class], version = 1)
abstract class LaunchesRoomDatabase : RoomDatabase() {
    abstract fun launchesDao(): LaunchesDao
}