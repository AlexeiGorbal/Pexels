package com.example.pexelsapp.domain

import com.example.pexelsapp.domain.remote.CollectionEntity
import com.example.pexelsapp.domain.remote.PexelsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollectionRepository @Inject constructor(private val api: PexelsApi) {

    suspend fun getFeaturedCollections(): List<Collection> {
        return api.getFeaturedCollections(COLLECTIONS_PER_PAGE).collections
            ?.mapNotNull(CollectionEntity::toModel) ?: emptyList()
    }

    companion object {

        private const val COLLECTIONS_PER_PAGE = 7
    }
}

private fun CollectionEntity.toModel(): Collection? {
    val title = title.takeUnless { it.isNullOrBlank() } ?: return null
    return Collection(title)
}