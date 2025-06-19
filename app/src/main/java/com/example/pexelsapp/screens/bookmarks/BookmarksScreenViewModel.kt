package com.example.pexelsapp.screens.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksScreenViewModel @Inject constructor(
    private val repository: PhotoRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<BookmarksScreenUiState>(BookmarksScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getFavoritePhotos().collect {
                if (it.isNotEmpty()) {
                    _uiState.value = BookmarksScreenUiState.Loaded(it)
                } else {
                    _uiState.value = BookmarksScreenUiState.NothingSaved
                }
            }
        }
    }
}