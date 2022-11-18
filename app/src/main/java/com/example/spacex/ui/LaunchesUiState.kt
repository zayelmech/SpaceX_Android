package com.example.spacex.ui

import com.example.spacex.database.LaunchRoomEntity

// Represents different states for the LatestNews screen
sealed class LaunchesUiState {
    data class Success(val launches: List<LaunchRoomEntity>) : LaunchesUiState()
    data class Error(val exception: Throwable) : LaunchesUiState()
    object Loading : LaunchesUiState()
}
