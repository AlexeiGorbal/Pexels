package com.example.pexelsapp.screens.bookmarks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pexelsapp.domain.Photo
import com.example.pexelsapp.screens.bookmarks.elements.ListBookmarks
import com.example.pexelsapp.screens.bookmarks.elements.NothingSavedView
import com.example.pexelsapp.screens.bookmarks.elements.Title
import com.example.pexelsapp.ui.components.PexelsLinearProgressIndicator

@Composable
fun BookmarksScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarksScreenViewModel = hiltViewModel(),
    onNavToDetailsScreen: (Long) -> Unit,
    onNavToHomeScreen: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier) {
        Title(modifier = Modifier.align(Alignment.CenterHorizontally))
        when (val state = uiState) {
            is BookmarksScreenUiState.NothingSaved -> {
                NothingSavedState(
                    onExploreClick = onNavToHomeScreen,
                    modifier = Modifier.fillMaxSize()
                )
            }

            is BookmarksScreenUiState.Loading -> {
                PexelsLinearProgressIndicator(
                    modifier = modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                )
            }

            is BookmarksScreenUiState.Loaded -> {
                LoadedState(
                    photos = state.favoritePhotos,
                    onNavToDetailsScreen = { photoId -> onNavToDetailsScreen(photoId) },
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp, top = 29.dp)
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun NothingSavedState(
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    NothingSavedView(
        onExploreClick = onExploreClick,
        modifier = modifier
    )
}

@Composable
fun LoadedState(
    photos: List<Photo>,
    onNavToDetailsScreen: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    ListBookmarks(
        photos = photos,
        onClick = { photoId -> onNavToDetailsScreen(photoId) },
        modifier = modifier
    )
}