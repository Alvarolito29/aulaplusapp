package com.example.prueba.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    
    // URL para emulador Android (localhost del PC)
    // Si usas dispositivo físico, usa tu IP local (ej: http://192.168.1.50:3015/api/)
    // Si despliegas en Render, usa la URL HTTPS (ej: https://aulaplus-api.onrender.com/api/)
    private const val BASE_URL = "http://10.0.2.2:3015/api/" 
    // private const val BASE_URL = "http://192.168.1.5:3015/api/" // Descomenta y pon tu IP si usas celular físico
    private const val EXTERNAL_API_URL = "https://api.quotable.io/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(AuthInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
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
