package com.example.prueba

import com.example.prueba.data.model.LoginResponse
import com.example.prueba.data.model.SchoolClass
import com.example.prueba.data.model.User
import com.example.prueba.data.network.ApiService
import com.example.prueba.data.repository.SchoolRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Response

class UnitTest {

    private lateinit var mockApiService: ApiService

    @BeforeEach
    fun setup() {
        mockApiService = mockk()
        // Inyectamos el mock del servicio
        SchoolRepository.setApiService(mockApiService)
        
        // Reemplazamos el logger para evitar errores de "Method e not mocked"
        SchoolRepository.logError = { tag, msg -> 
            println("TEST LOG [$tag]: $msg")
        }
    }

    @AfterEach
    fun tearDown() {
        SchoolRepository.setApiService(null)
        unmockkAll()
    }

    @Test
    fun loginWithDefaultAdmin_isSuccessful() = runTest {
        val fakeUser = User("admin@test.com", "", "Admin")
        val fakeResponse = LoginResponse("token", fakeUser)
        
        coEvery { mockApiService.login(any()) } returns Response.success(fakeResponse)

        val result = SchoolRepository.login("admin@test.com", "123456")
        assertNotNull(result, "El resultado del login no debería ser nulo")
        assertEquals("Admin", result?.user?.name)
    }

    @Test
    fun loginWithWrongCredentials_fails() = runTest {
        val errorBody = "{\"error\":\"Unauthorized\"}".toResponseBody("application/json".toMediaTypeOrNull())
        coEvery { mockApiService.login(any()) } returns Response.error(401, errorBody)
        
        val result = SchoolRepository.login("wrong@test.com", "wrongpass")
        assertNull(result, "El resultado debería ser nulo para credenciales incorrectas")
    }

    @Test
    fun registerNewUser_isSuccessful() = runTest {
        val newUser = User("new@test.com", "password", "New User")
        coEvery { mockApiService.register(any()) } returns Response.success(null)
        
        val result = SchoolRepository.register(newUser)
        assertTrue(result, "El registro debería devolver true")
    }

    @Test
    fun getSchedule_returnsList() = runTest {
        val mockSchedule = listOf(
            SchoolClass("1", "Matemáticas", "Prof. Jirafales", "Sala 1", "08:00", "09:30", "Lunes")
        )
        coEvery { mockApiService.getSchedule() } returns Response.success(mockSchedule)

        val schedule = SchoolRepository.getSchedule()
        assertTrue(schedule.isNotEmpty(), "El horario no debería estar vacío")
        assertEquals(1, schedule.size)
    }

    @Test
    fun checkClassStructure() = runTest {
        val mockSchedule = listOf(
            SchoolClass("1", "Matemáticas", "Prof. Jirafales", "Sala 1", "08:00", "09:30", "Lunes")
        )
        coEvery { mockApiService.getSchedule() } returns Response.success(mockSchedule)

        val schedule = SchoolRepository.getSchedule()
        val firstClass = schedule.first()
        assertNotNull(firstClass.subject)
        assertNotNull(firstClass.startTime)
        assertNotNull(firstClass.professor)
    }

    @Test
    fun filterScheduleBySubject_works() = runTest {
        val mockSchedule = listOf(
            SchoolClass("1", "Matemáticas", "Prof. Jirafales", "Sala 1", "08:00", "09:30", "Lunes"),
            SchoolClass("2", "Historia", "Prof. Banderas", "Sala 2", "10:00", "11:30", "Martes")
        )
        coEvery { mockApiService.getSchedule() } returns Response.success(mockSchedule)

        val schedule = SchoolRepository.getSchedule()
        val filtered = schedule.filter { it.subject.contains("Matemáticas", ignoreCase = true) }
        
        assertTrue(filtered.any { it.subject == "Matemáticas" })
        assertFalse(filtered.any { it.subject == "Historia" })
    }

    @Test
    fun filterScheduleByProfessor_works() = runTest {
        val mockSchedule = listOf(
            SchoolClass("1", "Física", "Einstein", "Laboratorio", "08:00", "09:30", "Lunes"),
            SchoolClass("2", "Química", "Walter White", "Laboratorio", "10:00", "11:30", "Martes")
        )
        coEvery { mockApiService.getSchedule() } returns Response.success(mockSchedule)

        val schedule = SchoolRepository.getSchedule()
        val filtered = schedule.filter { it.professor.contains("Einstein", ignoreCase = true) }
        
        assertTrue(filtered.any { it.professor.contains("Einstein") })
        assertTrue(filtered.all { it.subject == "Física" })
    }
}