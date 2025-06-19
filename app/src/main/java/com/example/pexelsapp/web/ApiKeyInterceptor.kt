package com.example.pexelsapp.web

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(val key: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request()
            .newBuilder()
            .header("Authorization", key)
            .build()
        return chain.proceed(newRequest)
    }
}