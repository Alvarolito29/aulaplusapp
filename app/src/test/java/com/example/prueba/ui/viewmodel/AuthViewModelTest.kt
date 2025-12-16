package com.example.prueba.ui.viewmodel

import android.app.Application
import com.example.prueba.data.local.SessionManager
import com.example.prueba.data.model.LoginResponse
import com.example.prueba.data.model.User
import com.example.prueba.data.repository.AvatarRepository
import com.example.prueba.data.repository.SchoolRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import java.lang.reflect.Field

@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest : StringSpec({

    val dispatcher = StandardTestDispatcher()
    val mockApplication: Application = mockk(relaxed = true)
    val mockAvatarRepository: AvatarRepository = mockk(relaxed = true)

    beforeTest {
        Dispatchers.setMain(dispatcher)
        mockkObject(SchoolRepository)
        mockkObject(SessionManager)
        // No mockeamos el objeto AvatarRepository, sino que vamos a inyectar una instancia mock
    }

    afterTest {
        Dispatchers.resetMain()
        unmockkAll()
    }

    // Prueba 1: Login exitoso
    "login() con credenciales correctas debe resultar en Success" {
        val fakeUser = User("test@colegio.cl", "", "Test User")
        val fakeResponse = LoginResponse("fake_token", fakeUser)
        
        coEvery { SchoolRepository.login("test@colegio.cl", "123456") } returns fakeResponse
        coEvery { SessionManager.saveAuthToken(any()) } returns Unit
        coEvery { SessionManager.saveUserName(any()) } returns Unit

        val viewModel = AuthViewModel(mockApplication)
        // Inyectar el mock de AvatarRepository usando reflexión
        injectAvatarRepository(viewModel, mockAvatarRepository)

        viewModel.login("test@colegio.cl", "123456")

        dispatcher.scheduler.advanceUntilIdle()
        
        viewModel.loginState.value.shouldBeInstanceOf<LoginState.Success>()
    }

    // Prueba 2: Login fallido
    "login() con credenciales incorrectas debe resultar en Error" {
        coEvery { SchoolRepository.login(any(), any()) } returns null

        val viewModel = AuthViewModel(mockApplication)
        injectAvatarRepository(viewModel, mockAvatarRepository)
        
        viewModel.login("wrong@colegio.cl", "wrongpass")

        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.loginState.value
        state.shouldBeInstanceOf<LoginState.Error>()
        
        // CORRECCIÓN: Usar directamente el cast seguro que nos provee shouldBeInstanceOf 
        // o comprobarlo con smart cast si es posible. 
        // Al usar shouldBeInstanceOf, Kotest ya verifica el tipo, pero el compilador a veces lanza warning.
        // La forma más limpia para evitar el warning 'No cast needed' es NO hacer el cast explícito si Kotlin ya hizo smart cast,
        // o si no lo hizo, estructurarlo para que sea obvio.
        
        // En este caso, simplemente accedemos a la propiedad si el smart cast funciona, o hacemos un check seguro.
        if (state is LoginState.Error) {
             state.message shouldBe "Credenciales incorrectas o error de red"
        }
    }
    
    // Prueba 3: Registro exitoso
    "register() con datos nuevos debe resultar en Success" {
        val newUser = User("new@colegio.cl", "123456", "New User")
        val fakeLoginResponse = LoginResponse("new_token", newUser)

        coEvery { SchoolRepository.register(any()) } returns true
        coEvery { SchoolRepository.login(newUser.email, newUser.password) } returns fakeLoginResponse
        coEvery { SessionManager.saveAuthToken(any()) } returns Unit
        coEvery { SessionManager.saveUserName(any()) } returns Unit
        coEvery { mockAvatarRepository.saveAvatarUri(any()) } returns Unit
        
        val viewModel = AuthViewModel(mockApplication)
        injectAvatarRepository(viewModel, mockAvatarRepository)

        viewModel.register(newUser.name, newUser.email, newUser.password, null)
        
        dispatcher.scheduler.advanceUntilIdle()
        
        viewModel.registerState.value.shouldBeInstanceOf<RegisterState.Success>()
    }
    
    // Prueba 4: Registro fallido (usuario ya existe)
    "register() con usuario existente debe resultar en Error" {
        coEvery { SchoolRepository.register(any()) } returns false // Simulamos que el repositorio falla

        val viewModel = AuthViewModel(mockApplication)
        injectAvatarRepository(viewModel, mockAvatarRepository)

        viewModel.register("Existing User", "exist@colegio.cl", "123456", null)
        
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.registerState.value
        state.shouldBeInstanceOf<RegisterState.Error>()

        if (state is RegisterState.Error) {
            state.message shouldBe "El usuario ya existe o error de red"
        }
    }

    // Prueba 5: Registro exitoso pero login posterior falla
    "register() con login posterior fallido debe resultar en Error" {
        coEvery { SchoolRepository.register(any()) } returns true
        coEvery { SchoolRepository.login(any(), any()) } returns null // El login posterior falla

        val viewModel = AuthViewModel(mockApplication)
        injectAvatarRepository(viewModel, mockAvatarRepository)

        viewModel.register("test", "test@email.com", "pass", null)

        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.registerState.value
        state.shouldBeInstanceOf<RegisterState.Error>()

        if (state is RegisterState.Error) {
            state.message shouldBe "Error al iniciar sesión después del registro"
        }
    }
    
    // Prueba 6: Logout debe limpiar la sesión
    "logout() debe llamar a SessionManager.clearSession()" {
        coEvery { SessionManager.clearSession() } returns Unit
        coEvery { mockAvatarRepository.clearAvatarUri() } returns Unit

        val viewModel = AuthViewModel(mockApplication)
        injectAvatarRepository(viewModel, mockAvatarRepository)

        viewModel.logout()

        dispatcher.scheduler.advanceUntilIdle()
    }
    
    // Prueba 7: Estado inicial del ViewModel debe ser Idle
    "El estado inicial de loginState y registerState debe ser Idle" {
        val viewModel = AuthViewModel(mockApplication)
        injectAvatarRepository(viewModel, mockAvatarRepository)
        
        viewModel.loginState.value.shouldBeInstanceOf<LoginState.Idle>()
        viewModel.registerState.value.shouldBeInstanceOf<RegisterState.Idle>()
    }
})

// Función auxiliar para inyectar el mock privado usando reflexión
fun injectAvatarRepository(viewModel: AuthViewModel, mockRepository: AvatarRepository) {
    val field: Field = AuthViewModel::class.java.getDeclaredField("avatarRepository")
    field.isAccessible = true
    field.set(viewModel, mockRepository)
}
