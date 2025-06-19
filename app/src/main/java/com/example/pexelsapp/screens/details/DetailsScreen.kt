package com.example.pexelsapp.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pexelsapp.web.download.AndroidDownloader
import com.example.pexelsapp.screens.details.elements.BookmarkButton
import com.example.pexelsapp.screens.details.elements.DownloadButton
import com.example.pexelsapp.screens.details.elements.ImageNotFoundView
import com.example.pexelsapp.screens.details.elements.SelectedPhoto
import com.example.pexelsapp.screens.details.elements.TopBarView
import com.example.pexelsapp.ui.components.PexelsLinearProgressIndicator

@Composable
fun DetailsScreen(
    photoId: Long,
    onBackPress: () -> Unit,
    onNavToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getPhotoById(photoId)
    }

    Scaffold(modifier = modifier) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            state?.also {
                TopBarView(
                    photographer = it.photo.photographer,
                    onBackPress = onBackPress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )
                when (uiState) {
                    DetailsScreenUiState.Loading -> {
                        PexelsLinearProgressIndicator(
                            modifier = modifier
                                .padding(top = 12.dp)
                                .fillMaxWidth()
                        )
                    }

                    DetailsScreenUiState.NotFound -> {
                        ImageNotFoundState(
                            onExploreClick = onNavToHomeScreen,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    DetailsScreenUiState.Loaded -> {
                        LoadedState(
                            photo = it.photo.url,
                            isFavourite = it.isFavourite,
                            onDownloadClick = { viewModel.onDownloadClick() },
                            onBookmarkClick = { viewModel.onBookmarkSavedStateChanged() },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImageNotFoundState(
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ImageNotFoundView(onExploreClick = onExploreClick, modifier = modifier)
}

@Composable
fun LoadedState(
    photo: String,
    isFavourite: Boolean,
    onDownloadClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SelectedPhoto(photo, Modifier.padding(start = 24.dp, end = 24.dp, top = 29.dp))
    Spacer(modifier)
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        DownloadButton(onClick = onDownloadClick)
        BookmarkButton(
            isSelected = isFavourite,
            onClick = onBookmarkClick
        )
    }
}