package com.example.pexelsapp.domain.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelsApi {

    @GET("collections/featured")
    suspend fun getFeaturedCollections(@Query("per_page") perPage: Int): CollectionsEntity

    @GET("curated")
    suspend fun getPhotos(@Query("per_page") perPage: Int): PhotosEntity

    @GET("search")
    suspend fun getSearchPhotos(
        @Query("query") inputText: String,
        @Query("per_page") perPage: Int,
    ): PhotosEntity

    @GET("photos/{photoId}")
    suspend fun getPhotoById(@Path("photoId") photoId: Long): PhotoEntity
}