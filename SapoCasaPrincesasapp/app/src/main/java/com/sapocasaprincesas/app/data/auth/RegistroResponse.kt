package com.sapocasaprincesas.app.data.auth

sealed class RegistroResponse {

    data class Exito(
        val mensaje: String
    ) : RegistroResponse()

    data class Error(
        val mensaje: String
    ) : RegistroResponse()
}





