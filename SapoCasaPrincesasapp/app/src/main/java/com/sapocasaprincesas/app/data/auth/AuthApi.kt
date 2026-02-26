package com.sapocasaprincesas.app.data.auth

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/login")
    suspend fun login(
        @Body request: LoginRequest
    ): UsuarioResponse

    @POST("/api/registro")
    suspend fun registrar(
        @Body request: RegistroRequest
    ): MensajeResponse

    @POST("/api/recuperar")
    suspend fun recuperar(
        @Body request: RecuperarRequest
    ): RecuperarBackendResponse
}









