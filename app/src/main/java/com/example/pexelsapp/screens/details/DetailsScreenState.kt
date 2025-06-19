package com.example.pexelsapp.screens.details

import com.example.pexelsapp.domain.Photo

data class DetailsScreenState(
    val photo: Photo,
    val isFavourite: Boolean,
)