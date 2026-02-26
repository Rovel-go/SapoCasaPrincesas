package com.sapocasaprincesas.app.data.auth.registro

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapocasaprincesas.app.data.auth.AuthRepository
import com.sapocasaprincesas.app.data.auth.RegistroResponse
import kotlinx.coroutines.launch

class RegistroViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    var estado = mutableStateOf(RegistroEstado())
        private set

    fun mostrarError(mensaje: String) {
        estado.value = estado.value.copy(
            cargando = false,
            resultado = RegistroResponse.Error(mensaje)
        )
    }

    fun registrar(
        nombre: String,
        apellidos: String,
        email: String,
        contrasena: String
    ) {
        estado.value = estado.value.copy(cargando = true)

        viewModelScope.launch {
            val respuesta = repository.registrar(
                nombre,
                apellidos,
                email,
                contrasena
            )

            estado.value = estado.value.copy(
                cargando = false,
                resultado = respuesta
            )
        }
    }
}
