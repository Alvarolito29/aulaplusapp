package com.example.prueba.data.network

import com.example.prueba.data.model.Quote
import retrofit2.Response
import retrofit2.http.GET

interface ExternalApiService {
    @GET("random")
    suspend fun getRandomQuote(): Response<Quote>
}
