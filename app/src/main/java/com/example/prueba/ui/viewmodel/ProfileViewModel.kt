package com.example.prueba.ui.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba.data.local.SessionManager
import com.example.prueba.data.model.User
import com.example.prueba.data.repository.AvatarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfileUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null,
    val avatarUri: Uri? = null
)

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val avatarRepository = AvatarRepository(application)

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadUserProfile()
        loadSavedAvatar()
    }

    private fun loadUserProfile() {
        // Recuperamos los datos reales de la sesión persistente
        val savedEmail = SessionManager.getAuthToken() ?: "invitado@colegio.cl"
        val savedName = SessionManager.getUserName() ?: "Usuario Invitado"

        _uiState.update { 
            it.copy(
                user = User(savedEmail, "", savedName),
                isLoading = false
            ) 
        }
    }

    // Cargar avatar desde DataStore reactivamente
    private fun loadSavedAvatar() {
        viewModelScope.launch {
            avatarRepository.getAvatarUri().collect { savedUri ->
                _uiState.update { it.copy(avatarUri = savedUri) }
            }
        }
    }

    // Guardar nuevo avatar en DataStore
    fun updateAvatar(uri: Uri?) {
        viewModelScope.launch {
            avatarRepository.saveAvatarUri(uri)
        }
    }
}