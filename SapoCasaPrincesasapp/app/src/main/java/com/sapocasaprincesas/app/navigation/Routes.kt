package com.sapocasaprincesas.app.navigation

sealed class Routes(val route: String) {

    // Pantallas generales
    object Login : Routes("login")
    object Registro : Routes("registro")
    object Recuperar : Routes("recuperar")
    object Home : Routes("home")

    // Salones
    object Salones : Routes("salones")

    object SalonDetalle :
        Routes("salonDetalle/{titulo}/{descripcion}/{imagen}") {

        fun createRoute(
            titulo: String,
            descripcion: String,
            imagen: String
        ): String =
            "salonDetalle/$titulo/$descripcion/$imagen"
    }

    // Colaboradores
    object Colaboradores : Routes("colaboradores")

    object ColaboradorDetalle :
        Routes("colaboradorDetalle/{nombre}/{descripcion}/{imagen}") {

        fun createRoute(
            nombre: String,
            descripcion: String,
            imagen: String
        ): String =
            "colaboradorDetalle/$nombre/$descripcion/$imagen"
    }

    // Categorías de servicios
    object ServiciosCategorias : Routes("serviciosCategorias")

    // Subservicios (Color, Cortar, Peinar)
    object SubServicios :
        Routes("subServicios/{categoria}/{imagen}") {

        fun createRoute(categoria: String, imagen: String): String =
            "subServicios/$categoria/$imagen"
    }

    // Detalle del servicio
    object ServicioDetalle :
        Routes("servicioDetalle/{titulo}/{descripcion}/{precio}/{imagen}") {

        fun createRoute(
            titulo: String,
            descripcion: String,
            precio: String,
            imagen: String
        ): String =
            "servicioDetalle/$titulo/$descripcion/$precio/$imagen"
    }
}


