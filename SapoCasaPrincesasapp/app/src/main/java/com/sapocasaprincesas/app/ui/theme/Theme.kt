package com.sapocasaprincesas.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// En esta parte defino mis esquemas de color.
// No estoy personalizando nada todavía, solo uso los valores por defecto de Material 3.
// Más adelante, si quiero, puedo cambiar colores, tipografías o formas.
private val LightColors = lightColorScheme()
private val DarkColors = darkColorScheme()

// Este es mi tema principal de la app.
// Aquí ya no uso SapoTypography porque eso me estaba generando errores.
// Material 3 ya trae su propio sistema de tipografías, así que lo uso directamente.
@Composable
fun SapoCasaPrincesasTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    // Selecciono si quiero modo claro u oscuro.
    // Por ahora lo dejo manual, pero luego puedo conectarlo al sistema.
    val colors = if (darkTheme) DarkColors else LightColors

    // Aquí aplico el tema Material 3 real.
    // Uso colorScheme y typography directamente desde MaterialTheme.
    MaterialTheme(
        colorScheme = colors,
        typography = MaterialTheme.typography,
        content = content
    )
}


