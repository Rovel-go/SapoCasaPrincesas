package com.sapocasaprincesas.app.data.auth

import com.google.gson.Gson
import retrofit2.HttpException

class AuthRepository(
    private val api: AuthApi = RetrofitClient.instance
) {


    // LOGIN

    suspend fun login(email: String, contrasena: String): UsuarioResponse {
        println("REPO → login(email=$email)")

        return try {
            val request = LoginRequest(email, contrasena)
            println("REPO → llamando backend…")

            api.login(request)

        } catch (e: HttpException) {
            println("REPO → ERROR HTTP: ${e.code()}")
            throw Exception(parsearError(e))

        } catch (e: Exception) {
            println("REPO → EXCEPCIÓN: ${e.message}")
            throw Exception("Error de red: ${e.message}")
        }
    }


    // REGISTRO

    suspend fun registrar(
        nombre: String,
        apellidos: String,
        email: String,
        contrasena: String
    ): RegistroResponse {

        println("REPO → registrar(email=$email)")

        return try {
            val request = RegistroRequest(nombre, apellidos, email, contrasena)
            println("REPO → llamando backend…")

            val respuesta = api.registrar(request)

            RegistroResponse.Exito(respuesta.mensaje)

        } catch (e: HttpException) {
            println("REPO → ERROR HTTP: ${e.code()}")
            val mensaje = parsearError(e)
            println("REPO → MENSAJE BACKEND: $mensaje")
            RegistroResponse.Error(mensaje)

        } catch (e: Exception) {
            println("REPO → EXCEPCIÓN: ${e.message}")
            RegistroResponse.Error("Error de red: ${e.message}")
        }
    }

    // RECUPERAR CONTRASEÑA

    suspend fun recuperar(email: String): RecuperarResponse {

        println("REPO → recuperar(email=$email)")

        return try {
            val request = RecuperarRequest(email, null)


            println("REPO → llamando backend…")

            val respuesta = api.recuperar(request)

            RecuperarResponse.Exito(
                mensaje = respuesta.mensaje,
                contrasenaTemporal = respuesta.contrasenaTemporal
            )

        } catch (e: HttpException) {
            println("REPO → ERROR HTTP: ${e.code()}")
            val mensaje = parsearError(e)
            println("REPO → MENSAJE BACKEND: $mensaje")
            RecuperarResponse.Error(mensaje)

        } catch (e: Exception) {
            println("REPO → EXCEPCIÓN: ${e.message}")
            RecuperarResponse.Error("Error de red: ${e.message}")
        }
    }


    // PARSEO DE ERRORES DEL BACKEND

    private fun parsearError(e: HttpException): String {
        return try {
            val json = e.response()?.errorBody()?.string()
            println("REPO → JSON ERROR → $json")

            if (json.isNullOrBlank()) {
                return "Error desconocido"
            }

            return try {
                val error = Gson().fromJson(json, ErrorBackend::class.java)
                error?.mensaje ?: json
            } catch (ex: Exception) {
                json
            }

        } catch (ex: Exception) {
            "Error en el servidor"
        }
    }

    private data class ErrorBackend(val mensaje: String)
}



