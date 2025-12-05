package com.example.prueba.data.network

import com.example.prueba.data.local.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        
        // Obtener token guardado, puede retornar null si no hay sesión
        val token = try {
            SessionManager.getAuthToken()
        } catch (e: Exception) {
            null
        }
        
        // Si existe, lo agregamos al header
        if (!token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        
        return chain.proceed(requestBuilder.build())
    }
}