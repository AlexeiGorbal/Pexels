package com.example.pexelsapp.domain.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotosEntity(
    @SerialName("photos") val photos: List<PhotoEntity>?,
)

@Serializable
data class PhotoEntity(
    @SerialName("id") val id: Long?,
    @SerialName("photographer") val photographer: String?,
    @SerialName("src") val src: SrcEntity?,
)

@Serializable
data class SrcEntity(
    @SerialName("original") val original: String?,
)