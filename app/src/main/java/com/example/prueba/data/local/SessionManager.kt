package com.example.prueba.data.local

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREF_NAME = "school_app_prefs"
    private const val KEY_TOKEN = "auth_token" // Guardamos el email aquí como "token"
    private const val KEY_USER_NAME = "user_name" // Nueva clave para el nombre
    
    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveAuthToken(email: String) {
        prefs.edit().putString(KEY_TOKEN, email).apply()
    }
    
    // Nueva función para guardar el nombre
    fun saveUserName(name: String) {
        prefs.edit().putString(KEY_USER_NAME, name).apply()
    }

    fun getAuthToken(): String? {
        if (!::prefs.isInitialized) return null
        return prefs.getString(KEY_TOKEN, null)
    }
    
    // Nueva función para recuperar el nombre
    fun getUserName(): String? {
        if (!::prefs.isInitialized) return null
        return prefs.getString(KEY_USER_NAME, null)
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
    
    fun isLoggedIn(): Boolean {
        return getAuthToken() != null
    }
}