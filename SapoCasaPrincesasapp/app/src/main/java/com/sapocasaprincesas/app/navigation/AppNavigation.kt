package com.sapocasaprincesas.app.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument

import com.sapocasaprincesas.app.R
import com.sapocasaprincesas.app.ui.components.Header
import com.sapocasaprincesas.app.ui.components.NavegadorPaginas
import com.sapocasaprincesas.app.ui.components.FooterNativo
import com.sapocasaprincesas.app.ui.components.NavItem

import com.sapocasaprincesas.app.ui.home.HomeScreen
import com.sapocasaprincesas.app.ui.login.LoginScreen
import com.sapocasaprincesas.app.ui.registro.RegistroScreen
import com.sapocasaprincesas.app.ui.recuperar.RecuperarContrasenaScreen

import com.sapocasaprincesas.app.ui.servicios.ServiciosCategoriasScreen
import com.sapocasaprincesas.app.ui.servicios.SubServiciosScreen
import com.sapocasaprincesas.app.ui.servicios.ServicioDetalleScreen

import com.sapocasaprincesas.app.ui.paginasInstitucionales.*
import com.sapocasaprincesas.app.ui.salones.SalonesScreen
import com.sapocasaprincesas.app.ui.salones.SalonDetalleScreen
import com.sapocasaprincesas.app.ui.colaboradores.ColaboradoresScreen
import com.sapocasaprincesas.app.ui.colaboradores.ColaboradorDetalleScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: ""

    val rutasSinEstructura = listOf(
        Routes.Login.route,
        Routes.Registro.route,
        Routes.Recuperar.route
    )

    val esPantallaSimple = rutasSinEstructura.any { currentRoute.startsWith(it) }

    val footerItems = listOf(
        NavItem("quienes", "Quiénes Somos"),
        NavItem("mision", "Misión"),
        NavItem("vision", "Visión"),
        NavItem("contacto", "Contáctanos")
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Header(
                navController = navController,
                onLogout = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(0)
                    }
                },
                currentRoute = currentRoute
            )

            if (!esPantallaSimple) {
                NavegadorPaginas(
                    navController = navController,
                    currentRoute = currentRoute
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Routes.Login.route,
                    modifier = Modifier.fillMaxSize()
                ) {

                    // LOGIN / REGISTRO / RECUPERAR
                    composable(Routes.Login.route) { LoginScreen(navController) }
                    composable(Routes.Registro.route) { RegistroScreen(navController) }
                    composable(Routes.Recuperar.route) { RecuperarContrasenaScreen(navController) }

                    // HOME
                    composable(Routes.Home.route) { HomeScreen(navController) }

                    // SERVICIOS
                    composable(Routes.ServiciosCategorias.route) {
                        ServiciosCategoriasScreen(navController)
                    }

                    composable(
                        route = Routes.SubServicios.route,
                        arguments = listOf(
                            navArgument("categoria") { type = NavType.StringType },
                            navArgument("imagen") { type = NavType.StringType }
                        )
                    ) { entry ->
                        val categoria = entry.arguments?.getString("categoria") ?: ""
                        val imagen = entry.arguments?.getString("imagen") ?: ""
                        SubServiciosScreen(navController, categoria, imagen)
                    }

                    composable(
                        route = Routes.ServicioDetalle.route,
                        arguments = listOf(
                            navArgument("titulo") { type = NavType.StringType },
                            navArgument("descripcion") { type = NavType.StringType },
                            navArgument("precio") { type = NavType.StringType },
                            navArgument("imagen") { type = NavType.StringType }
                        )
                    ) { entry ->
                        val titulo = entry.arguments?.getString("titulo") ?: ""
                        val descripcion = entry.arguments?.getString("descripcion") ?: ""
                        val precio = entry.arguments?.getString("precio") ?: ""
                        val imagen = entry.arguments?.getString("imagen") ?: ""

                        ServicioDetalleScreen(
                            navController,
                            titulo,
                            descripcion,
                            precio,
                            imagen
                        )
                    }

                    // PÁGINAS INSTITUCIONALES
                    composable("quienes") { QuienesSomosScreen() }
                    composable("mision") { MisionScreen() }
                    composable("vision") { VisionScreen() }
                    composable("contacto") { ContactoScreen() }

                    // ⭐ SALONES
                    composable(Routes.Salones.route) {
                        SalonesScreen(navController)
                    }

                    composable(
                        route = Routes.SalonDetalle.route,
                        arguments = listOf(
                            navArgument("titulo") { type = NavType.StringType },
                            navArgument("descripcion") { type = NavType.StringType },
                            navArgument("imagen") { type = NavType.StringType }
                        )
                    ) { entry ->
                        val titulo = entry.arguments?.getString("titulo") ?: ""
                        val descripcion = entry.arguments?.getString("descripcion") ?: ""
                        val imagen = entry.arguments?.getString("imagen") ?: ""

                        SalonDetalleScreen(navController, titulo, descripcion, imagen)
                    }

                    // ⭐ COLABORADORES — LISTA
                    composable(Routes.Colaboradores.route) {
                        ColaboradoresScreen(navController)
                    }

                    // ⭐ COLABORADOR DETALLE
                    composable(
                        route = Routes.ColaboradorDetalle.route,
                        arguments = listOf(
                            navArgument("nombre") { type = NavType.StringType },
                            navArgument("descripcion") { type = NavType.StringType },
                            navArgument("imagen") { type = NavType.StringType }
                        )
                    ) { entry ->
                        val nombre = entry.arguments?.getString("nombre") ?: ""
                        val descripcion = entry.arguments?.getString("descripcion") ?: ""
                        val imagen = entry.arguments?.getString("imagen") ?: ""

                        ColaboradorDetalleScreen(navController, nombre, descripcion, imagen)
                    }
                }
            }

            if (!esPantallaSimple) {
                FooterNativo(
                    navController = navController,
                    items = footerItems
                )
            }
        }
    }
}
