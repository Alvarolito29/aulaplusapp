package com.example.prueba.data.network

import com.example.prueba.data.model.LoginRequest
import com.example.prueba.data.model.LoginResponse
import com.example.prueba.data.model.RegisterRequest
import com.example.prueba.data.model.SchoolClass
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
    
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<Void>
    
    @GET("schedule")
    suspend fun getSchedule(): Response<List<SchoolClass>>
}