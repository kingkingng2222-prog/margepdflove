package com.example.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Lightweight and resilient network client for fetching dynamic JSON files
 * from GitHub or GitHub Pages without heavy overhead.
 */
object NetworkClient {

    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(12, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .build()
    }

    /**
     * Executes HTTP GET request asynchronously and returns the raw response body string.
     */
    suspend fun fetchString(url: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url(url)
                // Bypass intermediate proxy cache to ensure fresh GitHub contents
                .header("Cache-Control", "no-cache")
                .header("User-Agent", "MedhaMantraApp/1.0")
                .build()

            val response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                val body = response.body?.string()
                if (!body.isNullOrBlank()) {
                    Result.success(body)
                } else {
                    Result.failure(IOException("Empty response body from $url"))
                }
            } else {
                Result.failure(IOException("HTTP ${response.code}: ${response.message}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
