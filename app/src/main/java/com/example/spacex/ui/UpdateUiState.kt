package com.example.spacex.ui


// Represents different states for the LatestNews screen
sealed class UpdateUiState {
    data class Success(val message: String) : UpdateUiState()
    data class Error(val exception: Throwable) : UpdateUiState()
    object Loading : UpdateUiState()
}
