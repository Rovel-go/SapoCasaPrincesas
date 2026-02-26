package com.sapocasaprincesas.app.ui.login

import com.sapocasaprincesas.app.data.auth.UsuarioResponse

sealed class LoginEstado {
    object Idle : LoginEstado()
    object Cargando : LoginEstado()
    data class Exito(val usuario: UsuarioResponse) : LoginEstado()
    data class Error(val mensaje: String) : LoginEstado()
}

