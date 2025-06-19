package com.example.pexelsapp.domain.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionsEntity(
    @SerialName("collections") val collections: List<CollectionEntity>?,
)

@Serializable
data class CollectionEntity(
    @SerialName("title") val title: String?,
)