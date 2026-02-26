package com.sapocasaprincesas.app.data.auth

sealed class RecuperarResponse {
    data class Exito(
        val mensaje: String,
        val contrasenaTemporal: String
    ) : RecuperarResponse()

    data class Error(val mensaje: String) : RecuperarResponse()
}


