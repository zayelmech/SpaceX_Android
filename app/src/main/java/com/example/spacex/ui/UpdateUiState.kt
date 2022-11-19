package com.example.spacex.ui

import com.example.spacex.database.LaunchRoomEntity


// Represents different states for the LatestNews screen
sealed class UpdateUiState {
    data class Success(val message: String) : UpdateUiState()
    data class Error(val exception: Throwable) : UpdateUiState()
    object Loading : UpdateUiState()
}
