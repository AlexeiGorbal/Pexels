package com.example.pexelsapp.web.download

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import com.example.pexelsapp.BuildConfig

class AndroidDownloader(private val context: Context) : Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadImage(uri: String): Long {
        val request = DownloadManager.Request(uri.toUri())
            .setMimeType("image/jpeg")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("image/jpg")
            .addRequestHeader("Authorization", BuildConfig.PEXLES_API_KEY)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image/jpg")
        return downloadManager.enqueue(request)
    }
}