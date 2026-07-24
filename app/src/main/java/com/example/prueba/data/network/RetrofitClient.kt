package com.example.prueba.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    
    // --- CONFIGURACIÓN DE URL ---
    // REEMPLAZA LA SIGUIENTE LÍNEA CON TU URL REAL DE RENDER
    private const val BASE_URL = "PEGA_AQUÍ_TU_URL_DE_RENDER/api/" 
    
    private const val EXTERNAL_API_URL = "https://api.quotable.io/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(AuthInterceptor())
        .connectTimeout(60, TimeUnit.SECONDS) 
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    private val externalHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    // Cliente para tu Backend (NestJS)
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    // Cliente para API Externa (Frases)
    val externalApiService: ExternalApiService by lazy {
        Retrofit.Builder()
            .baseUrl(EXTERNAL_API_URL)
            .client(externalHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExternalApiService::class.java)
    }
}