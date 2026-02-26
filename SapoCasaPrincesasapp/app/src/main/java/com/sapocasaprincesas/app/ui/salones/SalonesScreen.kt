package com.sapocasaprincesas.app.ui.salones

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

data class SalonDTO(
    val nombre: String,
    val descripcion: String,
    val imagenResId: Int
)

@Composable
fun SalonesScreen(navController: NavController) {

    val salones = listOf(
        SalonDTO(
            "Sede Norte",
            "Especialistas en color y peinados.",
            R.drawable.sede_norte
        ),
        SalonDTO(
            "Sede Centro",
            "Atención premium y servicios completos.",
            R.drawable.sede_centro
        ),
        SalonDTO(
            "Sede Sur",
            "Ambiente familiar y estilistas expertos.",
            R.drawable.sede_sur
        )
    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        salones.forEach { salon ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clickable {
                        navController.navigate(
                            Routes.SalonDetalle.createRoute(
                                titulo = salon.nombre,
                                descripcion = salon.descripcion,
                                imagen = salon.imagenResId.toString()
                            )
                        )
                    },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f)
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = salon.imagenResId),
                        contentDescription = salon.nombre,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(140.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = salon.nombre,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}



