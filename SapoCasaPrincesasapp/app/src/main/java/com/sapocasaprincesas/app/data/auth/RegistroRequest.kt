package com.sapocasaprincesas.app.data.auth

data class RegistroRequest(
    val nombre: String,
    val apellidos: String,
    val email: String,
    val contrasena: String
)

