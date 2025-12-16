package com.example.prueba.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.prueba.ui.theme.SchoolPrimary
import com.example.prueba.ui.theme.SchoolSecondary
import com.example.prueba.ui.viewmodel.AuthViewModel
import com.example.prueba.ui.viewmodel.LoginState

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginState by viewModel.loginState.collectAsState()
    val quote by viewModel.quoteState.collectAsState()
    
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            viewModel.resetLoginState()
            navController.navigate("schedule") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Fondo decorativo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(SchoolPrimary, SchoolSecondary)
                    ),
                    shape = RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp)
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            
            AnimatedVisibility(
                visible = visible,
                enter = slideInVertically(initialOffsetY = { -40 }) + fadeIn()
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 8.dp,
                    modifier = Modifier.size(100.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Logo",
                            tint = SchoolPrimary,
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Bienvenido",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = SchoolPrimary
                )
            )
            
            // Frase del día (API Externa)
            quote?.let { q ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "\"${q.content}\"",
                            style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic),
                            textAlign = TextAlign.Center,
                            color = SchoolPrimary
                        )
                        Text(
                            text = "- ${q.author}",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.align(Alignment.End),
                            color = Color.Gray
                        )
                    }
                }
            } ?: Text("Cargando frase del día...", style = MaterialTheme.typography.labelSmall, color = Color.Gray)

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Institucional") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = SchoolSecondary,
                    unfocusedBorderColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = SchoolSecondary,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            
            // Recuperar Contraseña
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                TextButton(onClick = { navController.navigate("forgot_password") }) {
                    Text("¿Olvidaste tu contraseña?", color = SchoolSecondary, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.login(email, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SchoolPrimary),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                if (loginState is LoginState.Loading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("INICIAR SESIÓN", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            if (loginState is LoginState.Error) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = (loginState as LoginState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { navController.navigate("register") }) {
                Text(
                    text = "¿No tienes cuenta? Regístrate",
                    color = SchoolSecondary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
