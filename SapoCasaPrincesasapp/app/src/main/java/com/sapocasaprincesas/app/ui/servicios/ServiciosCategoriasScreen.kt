package com.sapocasaprincesas.app.ui.servicios

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sapocasaprincesas.app.R
import com.sapocasaprincesas.app.navigation.Routes

data class CategoriaDTO(
    val id: Int,
    val nombre: String,
    val imagenResId: Int
)

@Composable
fun ServiciosCategoriasScreen(navController: NavController) {

    val categorias = listOf(
        CategoriaDTO(1, "Color", R.drawable.color),
        CategoriaDTO(2, "Cortar", R.drawable.cortar),
        CategoriaDTO(3, "Peinar", R.drawable.peinar)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        categorias.forEach { categoria ->

            CategoriaCard(
                titulo = categoria.nombre,
                imagen = categoria.imagenResId,
                onClick = {
                    navController.navigate(
                        Routes.SubServicios.createRoute(
                            categoria = categoria.nombre,
                            imagen = categoria.imagenResId.toString()
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

