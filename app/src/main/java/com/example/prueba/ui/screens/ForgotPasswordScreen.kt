package com.example.prueba.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.prueba.ui.theme.SchoolPrimary
import com.example.prueba.ui.theme.SchoolSecondary
import com.example.prueba.ui.viewmodel.AuthViewModel

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var successMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = SchoolPrimary)
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Recuperar Contraseña",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = SchoolPrimary
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ingresa tu correo institucional y te enviaremos un enlace para restablecer tu contraseña.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Institucional") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    isLoading = true
                    viewModel.recoverPassword(email) { success ->
                        isLoading = false
                        if (success) {
                            successMessage = "Se ha enviado un correo de recuperación a $email"
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SchoolPrimary),
                enabled = !isLoading && email.isNotBlank()
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("ENVIAR CORREO", fontWeight = FontWeight.Bold)
                }
            }

            if (successMessage != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = SchoolSecondary.copy(alpha = 0.1f)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = successMessage!!,
                        color = SchoolSecondary,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Medium
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                TextButton(onClick = { navController.popBackStack() }) {
                    Text("Volver al Inicio de Sesión")
                }
            }
        }
    }
}
