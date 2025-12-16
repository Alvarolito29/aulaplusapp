package com.example.prueba.data.repository

import android.util.Log
import androidx.annotation.VisibleForTesting
import com.example.prueba.data.model.LoginRequest
import com.example.prueba.data.model.LoginResponse
import com.example.prueba.data.model.RegisterRequest
import com.example.prueba.data.model.SchoolClass
import com.example.prueba.data.model.User
import com.example.prueba.data.network.ApiService
import com.example.prueba.data.network.RetrofitClient

object SchoolRepository {

    private var testApiService: ApiService? = null

    // Logger function that can be swapped for testing
    @VisibleForTesting
    var logError: (String, String) -> Unit = { tag, msg -> 
        try {
            Log.e(tag, msg)
        } catch (e: RuntimeException) {
            // Fallback if Log.e is not mocked in tests
            println("ERROR [$tag]: $msg")
        }
    }

    private val apiService: ApiService
        get() = testApiService ?: RetrofitClient.apiService

    @VisibleForTesting
    fun setApiService(service: ApiService?) {
        testApiService = service
    }

    suspend fun login(email: String, password: String): LoginResponse? {
        return try {
            val request = LoginRequest(email, password)
            val response = apiService.login(request)
            if (response.isSuccessful) {
                response.body()
            } else {
                logError("SchoolRepository", "Login failed: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            logError("SchoolRepository", "Login exception: ${e.message}")
            null
        }
    }

    suspend fun register(user: User): Boolean {
        return try {
            // Ajuste crítico: mapear los campos del User al nuevo RegisterRequest que coincide con el DTO
            val request = RegisterRequest(
                email = user.email,
                password = user.password,
                role = "ESTUDIANTE", // Valor por defecto o dinámico si decides implementarlo en la UI
                nombre = user.name
                // telefono, grado, etc., usarán los valores por defecto del data class
            )
            val response = apiService.register(request)
            
            if (!response.isSuccessful) {
                logError("SchoolRepository", "Register failed: ${response.errorBody()?.string()}")
            }
            
            response.isSuccessful
        } catch (e: Exception) {
            logError("SchoolRepository", "Register exception: ${e.message}")
            false
        }
    }

    suspend fun getSchedule(): List<SchoolClass> {
        return try {
            val response = apiService.getSchedule()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                logError("SchoolRepository", "Get schedule failed: ${response.errorBody()?.string()}")
                emptyList()
            }
        } catch (e: Exception) {
            logError("SchoolRepository", "Get schedule exception: ${e.message}")
            emptyList()
        }
    }
}