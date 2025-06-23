package com.example.pexelsapp.screens.details

import com.example.pexelsapp.domain.Photo

sealed class DetailsScreenUiState {

    data object NotFound : DetailsScreenUiState()

    data object Loading : DetailsScreenUiState()

    data class Loaded(
        val photo: Photo,
        val isFavourite: Boolean,
    ) : DetailsScreenUiState()
}