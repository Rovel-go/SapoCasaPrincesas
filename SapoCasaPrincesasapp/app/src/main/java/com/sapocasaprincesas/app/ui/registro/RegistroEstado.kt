package com.sapocasaprincesas.app.data.auth.registro

import com.sapocasaprincesas.app.data.auth.RegistroResponse

data class RegistroEstado(
    val cargando: Boolean = false,
    val resultado: RegistroResponse? = null
)

