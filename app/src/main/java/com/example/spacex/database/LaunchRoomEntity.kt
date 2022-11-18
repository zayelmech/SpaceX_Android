package com.example.spacex.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spacex_table")
data class LaunchRoomEntity(
    val missionName: String,
    val rocketName: String,
    val dateOfLaunch: String,
    val imageUrl: String?,
    val site: String?,
    val success: Boolean?,
    val year: String?,
    val details: String?,
    val website: String?,
    @PrimaryKey
    val launchNumber: Int?
)