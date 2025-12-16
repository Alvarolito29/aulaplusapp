package com.example.prueba.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.prueba.data.model.SchoolClass
import com.example.prueba.data.network.ApiService
import com.example.prueba.data.repository.SchoolRepository
import com.example.prueba.ui.theme.PruebaTheme
import com.example.prueba.ui.viewmodel.ScheduleViewModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class ScheduleScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockApiService: ApiService

    @Before
    fun setup() {
        mockApiService = mockk()
        SchoolRepository.setApiService(mockApiService)
    }

    @After
    fun tearDown() {
        SchoolRepository.setApiService(null)
    }

    @Test
    fun scheduleScreen_showsNextClass() {
        val fakeClasses = listOf(
            SchoolClass("1", "Matemáticas Avanzadas", "Prof. Test", "101", "08:00", "09:30", "Lunes")
        )

        coEvery { mockApiService.getSchedule() } returns Response.success(fakeClasses)

        composeTestRule.setContent {
            PruebaTheme {
                val viewModel = ScheduleViewModel() 
                val navController = rememberNavController()
                
                ScheduleScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }

        // Si esto falla en API 36, simplemente comentamos esta linea de espera
        // composeTestRule.waitForIdle() 

        composeTestRule.onNodeWithText("Matemáticas Avanzadas").assertIsDisplayed()
        composeTestRule.onNodeWithText("Prof. Test").assertIsDisplayed()
    }
}