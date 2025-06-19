package com.example.pexelsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pexelsapp.domain.local.PhotoDao
import com.example.pexelsapp.domain.local.PhotoDbEntity

@Database(
    entities = [PhotoDbEntity::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getPhotoDao(): PhotoDao
}