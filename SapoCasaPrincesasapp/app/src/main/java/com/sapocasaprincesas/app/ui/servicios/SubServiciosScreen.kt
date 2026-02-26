package com.sapocasaprincesas.app.ui.servicios

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sapocasaprincesas.app.navigation.Routes

data class SubServicioDTO(
    val titulo: String,
    val descripcion: String,
    val precio: String
)

@Composable
fun SubServiciosScreen(
    navController: NavController,
    categoria: String,
    imagen: String
) {

    val subServicios = when (categoria) {
        "Color" -> listOf(
            SubServicioDTO("Color Fantasía", "Aplicación de color fantasía completo.", "$ 120.000"),
            SubServicioDTO("Mechas", "Técnica de iluminación parcial.", "$ 150.000"),
            SubServicioDTO("Baño de color", "Refrescamiento de tono.", "$ 80.000")
        )
        "Cortar" -> listOf(
            SubServicioDTO("Corte en seco", "Corte de precisión en seco.", "$ 60.000"),
            SubServicioDTO("Corte con lavado", "Lavado + corte + secado básico.", "$ 75.000")
        )
        "Peinar" -> listOf(
            SubServicioDTO("Peinado ondas", "Ondas suaves tipo princesa.", "$ 70.000"),
            SubServicioDTO("Recogido", "Recogido elegante para evento.", "$ 110.000")
        )
        else -> emptyList()
    }

    val imagenIdString = imagen

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Servicios de $categoria",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        subServicios.forEach { servicio ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate(
                            Routes.ServicioDetalle.createRoute(
                                titulo = servicio.titulo,
                                descripcion = servicio.descripcion,
                                precio = servicio.precio,
                                imagen = imagenIdString
                            )
                        )
                    },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = servicio.titulo,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = servicio.descripcion,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = servicio.precio,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}


