package com.sapocasaprincesas.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sapocasaprincesas.app.navigation.Routes

@Composable
fun NavegadorPaginas(
    navController: NavController,
    currentRoute: String
) {
    val disabled = currentRoute in listOf(
        Routes.Login.route,
        Routes.Registro.route,
        Routes.Recuperar.route
    )

    val paginas = listOf(
        NavItem(Routes.ServiciosCategorias.route, label = "Servicios"),
        NavItem(Routes.Salones.route, label = "Salones"),
        NavItem(Routes.Colaboradores.route, label = "Colaboradores")
    )


    CompositionLocalProvider(
        LocalContentColor provides Color(0xFF12ABE7)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White.copy(alpha = 0.04f),
                    shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                )
                .padding(vertical = 6.dp)
                .alpha(if (disabled) 0.4f else 1f)
        ) {
            MenuNavegacion(
                navController = navController,
                items = paginas,
                disabled = disabled,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}








