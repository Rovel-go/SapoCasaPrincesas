package com.sapocasaprincesas.app.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Componente de mensaje reutilizable.
 * Replica el comportamiento de Mensaje.jsx + Mensaje.css,
 * pero optimizado para Android.
 * Lo uso para mostrar mensajes de error, éxito, proceso o información.
 * Incluye animación fadeIn como en el frontend.
 */
@Composable
fun Mensaje(
    texto: String,
    tipo: String = "error", // error, exito, proceso, info
    modifier: Modifier = Modifier
) {
    // Animación fadeIn (opacidad)
    var visible by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    // Activo la animación al montar el componente
    LaunchedEffect(Unit) {
        visible = true
    }


    val (bgColor, textColor, borderColor) = when (tipo) {
        "exito" -> Triple(Color(0xFFE6FFE6), Color(0xFF006600), Color(0xFF009900))
        "proceso" -> Triple(Color(0xFFE6F0FF), Color(0xFF003399), Color(0xFF3366FF))
        "info" -> Triple(Color(0xFFFFF7E6), Color(0xFFB36B00), Color(0xFFFF9900))
        else -> Triple(Color(0xFFFFE5E5), Color(0xFFB30000), Color(0xFFB30000)) // error
    }

    Box(
        modifier = modifier
            .fillMaxWidth(0.85f) // equivalente a tu width: 80%
            .padding(vertical = 8.dp)
            .alpha(alphaAnim.value)
            .background(
                color = bgColor,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(vertical = 12.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            color = textColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

