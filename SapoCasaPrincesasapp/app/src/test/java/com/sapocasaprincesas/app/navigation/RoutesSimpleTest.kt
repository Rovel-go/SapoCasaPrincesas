package com.sapocasaprincesas.app.navigation

import org.junit.Test
import org.junit.Assert.assertEquals

class RoutesSimpleTest {

    @Test
    fun rutasSimples_estanCorrectas() {
        assertEquals("login", Routes.Login.route)
        assertEquals("registro", Routes.Registro.route)
        assertEquals("recuperar", Routes.Recuperar.route)
        assertEquals("home", Routes.Home.route)
        assertEquals("salones", Routes.Salones.route)
        assertEquals("colaboradores", Routes.Colaboradores.route)
        assertEquals("serviciosCategorias", Routes.ServiciosCategorias.route)
    }
}

