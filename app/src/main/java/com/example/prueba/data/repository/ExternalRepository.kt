package com.example.prueba.data.repository

import com.example.prueba.data.model.Quote
import com.example.prueba.data.network.RetrofitClient

object ExternalRepository {
    suspend fun getRandomQuote(): Quote? {
        return try {
            val response = RetrofitClient.externalApiService.getRandomQuote()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
