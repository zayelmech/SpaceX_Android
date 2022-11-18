package com.example.spacex.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchesDao {

    /**
     * To observe data changes you will use Flow from kotlinx-coroutines.
     *  Use a return value of type Flow in your method description,
     *  and Room generates all necessary code to update the Flow when
     *  the database is updated.
     */
    @Query("SELECT * FROM spacex_table ORDER BY launchNumber ")
    suspend fun getAllLaunches(): List<LaunchRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewLaunch(launch: LaunchRoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateLaunches(launches: List<LaunchRoomEntity>)

}