package com.example.prueba.ui.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba.data.local.SessionManager
import com.example.prueba.data.model.User
import com.example.prueba.data.repository.AvatarRepository
import com.example.prueba.data.repository.SchoolRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    
    private val avatarRepository = AvatarRepository(application)

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            val loginResponse = SchoolRepository.login(email, pass)
            
            if (loginResponse != null) {
                // Guardamos token y datos del usuario desde la API
                SessionManager.saveAuthToken(loginResponse.token)
                SessionManager.saveUserName(loginResponse.user.name)
                
                _loginState.value = LoginState.Success
            } else {
                _loginState.value = LoginState.Error("Credenciales incorrectas o error de red")
            }
        }
    }

    fun register(name: String, email: String, pass: String, avatarUri: Uri?) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            val success = SchoolRepository.register(User(email, pass, name))
            if (success) {
                // Si el registro es exitoso, hacemos login para obtener el token
                val loginResponse = SchoolRepository.login(email, pass)
                if (loginResponse != null) {
                    SessionManager.saveAuthToken(loginResponse.token)
                    SessionManager.saveUserName(loginResponse.user.name)
                    avatarUri?.let { avatarRepository.saveAvatarUri(it) }
                    _registerState.value = RegisterState.Success
                } else {
                     _registerState.value = RegisterState.Error("Error al iniciar sesión después del registro")
                }
            } else {
                _registerState.value = RegisterState.Error("El usuario ya existe o error de red")
            }
        }
    }
    
    fun logout() {
        viewModelScope.launch {
            SessionManager.clearSession()
            avatarRepository.clearAvatarUri()
        }
    }

    fun resetLoginState() {
        _loginState.value = LoginState.Idle
    }

    fun resetRegisterState() {
        _registerState.value = RegisterState.Idle
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}