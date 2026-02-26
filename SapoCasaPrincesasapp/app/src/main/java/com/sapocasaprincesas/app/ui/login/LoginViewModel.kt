package com.sapocasaprincesas.app.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapocasaprincesas.app.data.auth.AuthRepository
import com.sapocasaprincesas.app.data.auth.UsuarioResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repo = AuthRepository()

    private val _estado = MutableStateFlow<LoginEstado>(LoginEstado.Idle)
    val estado: StateFlow<LoginEstado> = _estado

    fun login(email: String, contrasena: String) {

        println("VIEWMODEL → login llamado con email=$email")

        _estado.value = LoginEstado.Cargando

        viewModelScope.launch {

            try {
                println("VIEWMODEL → llamando repo.login()")

                // ✔ Ahora repo.login() devuelve UsuarioResponse
                val usuario: UsuarioResponse = repo.login(email, contrasena)

                println("VIEWMODEL → Login exitoso: ${usuario.email}")

                // ✔ LoginEstado.Exito recibe UsuarioResponse
                _estado.value = LoginEstado.Exito(usuario)

            } catch (e: Exception) {
                println("VIEWMODEL → ERROR: ${e.message}")
                _estado.value = LoginEstado.Error(e.message ?: "Error desconocido")
            }
        }
    }
}

