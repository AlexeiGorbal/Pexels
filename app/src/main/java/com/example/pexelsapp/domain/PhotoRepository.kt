package com.example.pexelsapp.domain

import com.example.pexelsapp.domain.local.PhotoDao
import com.example.pexelsapp.domain.local.PhotoDbEntity
import com.example.pexelsapp.domain.remote.PexelsApi
import com.example.pexelsapp.domain.remote.PhotoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepository @Inject constructor(
    private val dao: PhotoDao,
    private val api: PexelsApi,
) {

    suspend fun getPhotos(): List<Photo> {
        return api.getPhotos(PHOTOS_PER_PAGE).photos
            ?.mapNotNull(PhotoEntity::toModel) ?: emptyList()
    }

    suspend fun getSearchPhotos(inputText: String): List<Photo> {
        return api.getSearchPhotos(inputText, PHOTOS_PER_PAGE).photos
            ?.mapNotNull(PhotoEntity::toModel) ?: emptyList()
    }

    suspend fun getPhotoById(photoId: Long): Photo? {
        return api.getPhotoById(photoId).toModel()
    }

    suspend fun saveFavouritePhoto(photo: Photo) {
        dao.savePhoto(photo.toDbEntity())
    }

    suspend fun deleteFavouritePhoto(photo: Photo) {
        dao.deletePhoto(photo.toDbEntity())
    }

    suspend fun getFavouritePhotoById(photoId: Long): Photo? {
        return dao.getPhotoById(photoId)?.toModel()
    }

    fun getFavoritePhotos(): Flow<List<Photo>> {
        return dao.getPhotos()
            .map { it.map(PhotoDbEntity::toModel) }
    }

    companion object {

        private const val PHOTOS_PER_PAGE = 30
    }
}

private fun PhotoEntity.toModel(): Photo? {
    val id = id ?: return null
    val photographer = photographer.takeUnless { it.isNullOrBlank() } ?: return null
    val url = src?.original.takeUnless { it.isNullOrBlank() } ?: return null
    return Photo(id, photographer, url)
}

private fun PhotoDbEntity.toModel(): Photo {
    return Photo(id, photographer, url)
}

private fun Photo.toDbEntity(): PhotoDbEntity {
    return PhotoDbEntity(id, photographer, url)
}