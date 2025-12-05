package com.example.prueba.ui.screens

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.prueba.MainActivity
import com.example.prueba.data.model.SchoolClass
import com.example.prueba.data.repository.SchoolRepository
import com.example.prueba.ui.theme.PruebaTheme
import com.example.prueba.ui.viewmodel.ScheduleViewModel
import io.mockk.coEvery
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ScheduleScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        mockkObject(SchoolRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun scheduleScreen_showsNextClass() {
        // Datos simulados
        val fakeClasses = listOf(
            SchoolClass("1", "Matemáticas Avanzadas", "Prof. Test", "101", "08:00", "09:30", "Lunes")
        )

        // Configuramos el repositorio para devolver nuestros datos falsos
        coEvery { SchoolRepository.getSchedule() } returns fakeClasses

        // Para reemplazar el contenido de la actividad en un test de UI,
        // lo ideal es usar runOnUiThread y llamar a setContent (extensión de ComponentActivity).
        composeTestRule.activity.runOnUiThread {
            composeTestRule.activity.setContent {
                PruebaTheme {
                    val viewModel = ScheduleViewModel() 
                    val navController = rememberNavController()
                    
                    ScheduleScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }

        // Esperamos a que la UI se estabilice después de cambiar el contenido
        composeTestRule.waitForIdle()

        // Verificamos que el título de la próxima clase aparece
        composeTestRule.onNodeWithText("Matemáticas Avanzadas").assertIsDisplayed()
        composeTestRule.onNodeWithText("Prof. Test").assertIsDisplayed()
    }
}