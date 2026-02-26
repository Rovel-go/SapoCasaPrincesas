package com.sapocasaprincesas.app.data.auth.recuperar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapocasaprincesas.app.data.auth.AuthRepository
import com.sapocasaprincesas.app.data.auth.RecuperarResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecuperarViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    var estado = RecuperarEstado()
        private set

    fun mostrarError(mensaje: String) {
        estado = estado.copy(
            cargando = false,
            resultado = RecuperarResponse.Error(mensaje)
        )
    }

    fun recuperar(email: String) {
        estado = estado.copy(cargando = true)

        viewModelScope.launch(Dispatchers.IO) {

            val respuesta = repository.recuperar(email)

            withContext(Dispatchers.Main) {
                estado = estado.copy(
                    cargando = false,
                    resultado = respuesta
                )
            }
        }
    }
}





