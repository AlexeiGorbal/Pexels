package com.example.pexelsapp.screens.bookmarks

import com.example.pexelsapp.domain.Photo

sealed class BookmarksScreenUiState {

    data object NothingSaved : BookmarksScreenUiState()

    data object Loading : BookmarksScreenUiState()

    data class Loaded(val favoritePhotos: List<Photo>) : BookmarksScreenUiState()
}