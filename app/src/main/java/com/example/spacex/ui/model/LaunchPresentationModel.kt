package com.example.spacex.ui.model

data class LaunchPresentationModel(
     val missionName: String?,
    val rocketName: String?,
    val dateOfLaunch: String?,
    val imageUrl: String?,
    val site: String?,
    val success: Boolean?,
    val year: String?,
    val details: String?,
    val website: String?,
    val launchNumber: Int?
)
