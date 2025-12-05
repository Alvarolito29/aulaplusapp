package com.example.prueba.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prueba.data.local.SessionManager
import com.example.prueba.ui.screens.LoginScreen
import com.example.prueba.ui.screens.ProfileScreen
import com.example.prueba.ui.screens.RegisterScreen
import com.example.prueba.ui.screens.ScheduleScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    // Determinar el destino inicial basado en la sesión
    val startDestination = if (SessionManager.isLoggedIn()) "schedule" else "login"
    
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(navController)
        }
        composable("register") {
            RegisterScreen(navController)
        }
        composable("schedule") {
            ScheduleScreen(navController)
        }
        composable("profile") {
            ProfileScreen(navController)
        }
    }
}