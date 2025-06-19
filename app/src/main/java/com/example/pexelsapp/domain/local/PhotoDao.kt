package com.example.pexelsapp.domain.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Insert
    suspend fun savePhoto(photo: PhotoDbEntity)

    @Delete
    suspend fun deletePhoto(photo: PhotoDbEntity)

    @Query("SELECT * FROM Photo WHERE id=:photoId")
    suspend fun getPhotoById(photoId: Long): PhotoDbEntity?

    @Query("SELECT * FROM Photo")
    fun getPhotos(): Flow<List<PhotoDbEntity>>
}