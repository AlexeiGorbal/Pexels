package com.example.pexelsapp.web.download

interface Downloader {
    fun downloadImage(uri: String): Long
}