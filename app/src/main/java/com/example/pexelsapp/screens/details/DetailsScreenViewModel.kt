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

    private val _state = MutableStateFlow<DetailsScreenState?>(null)
    val state = _state.asStateFlow()

    fun onDownloadClick() {
        val prevState = _state.value ?: return
        downloader.downloadImage(prevState.photo.url)
    }

    fun getPhotoById(photoId: Long) {
        _uiState.value = DetailsScreenUiState.Loading
        viewModelScope.launch {
            try {
                val photo = repository.getPhotoById(photoId)
                val savedPhoto = repository.getFavouritePhotoById(photoId)
                if (savedPhoto == null) {
                    _state.value = DetailsScreenState(photo!!, false)
                } else {
                    _state.value = DetailsScreenState(savedPhoto, true)
                }
                _uiState.value = DetailsScreenUiState.Loaded
            } catch (e: Exception) {
                _uiState.value = DetailsScreenUiState.NotFound
            }
        }
    }

    fun onBookmarkSavedStateChanged() {
        viewModelScope.launch {
            val currentState = _state.value ?: return@launch
            val savedPhoto = repository.getFavouritePhotoById(currentState.photo.id)
            if (savedPhoto == null) {
                repository.saveFavouritePhoto(currentState.photo)
                _state.value = currentState.copy(isFavourite = true)
            } else {
                repository.deleteFavouritePhoto(currentState.photo)
                _state.value = currentState.copy(isFavourite = false)
            }
        }
    }
}