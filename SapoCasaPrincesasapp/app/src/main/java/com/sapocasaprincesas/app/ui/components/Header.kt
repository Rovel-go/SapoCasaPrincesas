package com.sapocasaprincesas.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.sapocasaprincesas.app.navigation.Routes

@Composable
fun Header(
    navController: NavController,
    onLogout: () -> Unit,
    currentRoute: String?
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MenuEnlaces(
            showHome = currentRoute !in listOf(
                Routes.Login.route,
                Routes.Registro.route,
                Routes.Recuperar.route
            ),
            showSocialIcons = currentRoute !in listOf(
                Routes.Login.route,
                Routes.Registro.route,
                Routes.Recuperar.route
            ),
            showLogout = currentRoute != Routes.Login.route,
            navController = navController,
            onLogout = onLogout
        )

        BrandContent()
    }
}




