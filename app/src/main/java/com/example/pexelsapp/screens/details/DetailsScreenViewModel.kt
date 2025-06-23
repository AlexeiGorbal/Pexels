package com.example.pexelsapp.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.PhotoRepository
import com.example.pexelsapp.web.download.Downloader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val downloader: Downloader,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailsScreenUiState>(DetailsScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun onDownloadClick() {
        val prevState = _uiState.value
        if (prevState is DetailsScreenUiState.Loaded)
            downloader.downloadImage(prevState.photo.url)
    }

    fun getPhotoById(photoId: Long) {
        _uiState.value = DetailsScreenUiState.Loading
        viewModelScope.launch {
            try {
                val photo = repository.getPhotoById(photoId)
                val savedPhoto = repository.getFavouritePhotoById(photoId)
                if (savedPhoto == null) {
                    if (photo != null) {
                        _uiState.value = DetailsScreenUiState.Loaded(photo, false)
                    } else {
                        _uiState.value = DetailsScreenUiState.NotFound
                    }
                } else {
                    _uiState.value = DetailsScreenUiState.Loaded(savedPhoto, true)
                }
            } catch (e: Exception) {
                _uiState.value = DetailsScreenUiState.NotFound
            }
        }
    }

    fun onBookmarkSavedStateChanged() {
        viewModelScope.launch {
            val currentState = _uiState.value
            if (currentState is DetailsScreenUiState.Loaded) {
                val savedPhoto = repository.getFavouritePhotoById(currentState.photo.id)
                if (savedPhoto == null) {
                    repository.saveFavouritePhoto(currentState.photo)
                    _uiState.value = currentState.copy(isFavourite = true)
                } else {
                    repository.deleteFavouritePhoto(currentState.photo)
                    _uiState.value = currentState.copy(isFavourite = false)
                }
            }
        }
    }
}