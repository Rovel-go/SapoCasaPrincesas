package com.sapocasaprincesas.app.navigation

import org.junit.Test
import org.junit.Assert.assertEquals

class RoutesParametrosTest {

    @Test
    fun salonDetalle_createRoute_esCorrecto() {
        val ruta = Routes.SalonDetalle.createRoute(
            titulo = "Corte",
            descripcion = "Moderno",
            imagen = "img1.png"
        )
        assertEquals("salonDetalle/Corte/Moderno/img1.png", ruta)
    }

    @Test
    fun colaboradorDetalle_createRoute_esCorrecto() {
        val ruta = Routes.ColaboradorDetalle.createRoute(
            nombre = "Ana",
            descripcion = "Experta",
            imagen = "ana.png"
        )
        assertEquals("colaboradorDDetalle/Ana/Experta/ana.png", ruta)
    }

    @Test
    fun subServicios_createRoute_esCorrecto() {
        val ruta = Routes.SubServicios.createRoute(
            categoria = "Color",
            imagen = "color.png"
        )
        assertEquals("subServicios/Color/color.png", ruta)
    }

    @Test
    fun servicioDetalle_createRoute_esCorrecto() {
        val ruta = Routes.ServicioDetalle.createRoute(
            titulo = "Peinado",
            descripcion = "Elegante",
            precio = "25000",
            imagen = "peinado.png"
        )
        assertEquals("servicioDetalle/Peinado/Elegante/25000/peinado.png", ruta)
    }
}

