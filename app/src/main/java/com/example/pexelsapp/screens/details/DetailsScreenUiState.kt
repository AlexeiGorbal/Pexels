package com.example.pexelsapp.screens.details

sealed class DetailsScreenUiState {

    data object NotFound : DetailsScreenUiState()

    data object Loading : DetailsScreenUiState()

    data object Loaded : DetailsScreenUiState()
}