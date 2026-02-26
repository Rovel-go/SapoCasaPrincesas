package com.sapocasaprincesas.app.ui.colaboradores

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sapocasaprincesas.app.R
import com.sapocasaprincesas.app.navigation.Routes

data class ColaboradorDTO(
    val nombre: String,
    val descripcion: String,
    val imagenResId: Int
)

@Composable
fun ColaboradoresScreen(navController: NavController) {

    val colaboradores = listOf(
        ColaboradorDTO("Mike", "Especialista en cortes modernos.", R.drawable.mike),
        ColaboradorDTO("Salome", "Experta en color y mechas.", R.drawable.salome),
        ColaboradorDTO("Rey", "Barbero profesional y estilista.", R.drawable.rey),
        ColaboradorDTO("Carolina", "Maquilladora y estilista integral.", R.drawable.carolina)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(colaboradores) { colaborador ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(
                            Routes.ColaboradorDetalle.createRoute(
                                colaborador.nombre,
                                colaborador.descripcion,
                                colaborador.imagenResId.toString()
                            )
                        )
                    },
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = colaborador.imagenResId),
                        contentDescription = colaborador.nombre,
                        modifier = Modifier.size(90.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = colaborador.nombre,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = colaborador.descripcion,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

