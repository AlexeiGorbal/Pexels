package com.example.pexelsapp.domain.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Photo")
data class PhotoDbEntity(
    @PrimaryKey
    @ColumnInfo("id") val id: Long,
    @ColumnInfo("photographer") val photographer: String,
    @ColumnInfo("url") val url: String,
)