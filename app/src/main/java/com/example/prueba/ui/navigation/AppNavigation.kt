package com.example.prueba.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prueba.data.local.SessionManager
import com.example.prueba.ui.screens.ForgotPasswordScreen
import com.example.prueba.ui.screens.LoginScreen
import com.example.prueba.ui.screens.ProfileScreen
import com.example.prueba.ui.screens.RegisterScreen
import com.example.prueba.ui.screens.ScheduleScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    // Determinar el destino inicial basado en la sesión
    val startDestination = if (SessionManager.isLoggedIn()) "schedule" else "login"
    
    NavHost(
        navController = navController, 
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(400))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(400))
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(400))
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(400))
        }
    ) {
        composable("login") {
            LoginScreen(navController)
        }
        composable("register") {
            RegisterScreen(navController)
        }
        composable("forgot_password") {
            ForgotPasswordScreen(navController)
        }
        composable("schedule") {
            ScheduleScreen(navController)
        }
        composable("profile") {
            ProfileScreen(navController)
        }
    }
}
