package com.example.pexelsapp.db

import android.content.Context
import androidx.room.Room
import com.example.pexelsapp.domain.local.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun providePhotoDao(appDataBase: AppDataBase): PhotoDao {
        return appDataBase.getPhotoDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "pexels_app_database")
            .build()
    }
}