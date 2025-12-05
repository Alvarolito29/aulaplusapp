package com.example.prueba.data.model

data class SchoolClass(
    val id: String,
    val subject: String, // Ej: Matemáticas
    val professor: String, // Ej: Prof. Jirafales
    val room: String, // Ej: Sala 3B
    val startTime: String, // Ej: "08:30"
    val endTime: String, // Ej: "10:00"
    val day: String // Ej: "Lunes"
)