package com.sapocasaprincesas.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Esta pantalla es temporal. La uso solo para validar que mi navegación funciona.
// Luego la elimino cuando ya tenga mis pantallas reales.
@Composable
fun PlaceholderScreen(nombrePantalla: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = nombrePantalla)
    }
}

