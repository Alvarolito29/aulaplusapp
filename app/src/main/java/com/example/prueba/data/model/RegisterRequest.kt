package com.example.prueba.data.model

data class RegisterRequest(
    val email: String,
    val password: String,
    val role: String,
    val nombre: String,
    // Campos opcionales requeridos por DTO pero con valores default para registro básico
    val telefono: String = "",
    val grado: String = "1° Medio",
    val seccion: String = "A",
    val apoderado: String = ""
)