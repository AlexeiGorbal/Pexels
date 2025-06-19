package com.example.pexelsapp.web

import android.content.Context
import com.example.pexelsapp.BuildConfig
import com.example.pexelsapp.domain.remote.PexelsApi
import com.example.pexelsapp.web.download.AndroidDownloader
import com.example.pexelsapp.web.download.Downloader
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideDownloader(@ApplicationContext context: Context): Downloader {
        return AndroidDownloader(context)
    }

    @Singleton
    @Provides
    fun providePexelsApi(okHttpClient: OkHttpClient): PexelsApi {
        val client = okHttpClient.newBuilder()
            .addInterceptor(ApiKeyInterceptor(BuildConfig.PEXLES_API_KEY))
            .build()

        val converterFactory = run {
            val contentType = "application/json".toMediaType()
            Json { ignoreUnknownKeys = true }.asConverterFactory(contentType)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.PEXLES_BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()

        return retrofit.create(PexelsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val cacheSize = 10 * 1024 * 1024L
        val cache = Cache(context.cacheDir, cacheSize)
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(logger)
            .build()
    }
}