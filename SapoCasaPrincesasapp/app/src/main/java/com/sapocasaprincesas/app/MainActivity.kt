package com.sapocasaprincesas.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.sapocasaprincesas.app.navigation.AppNavigation
import com.sapocasaprincesas.app.ui.theme.SapoCasaPrincesasTheme
import androidx.navigation.compose.rememberNavController





// Esta es mi actividad principal. Aquí inicializo toda mi app con Compose.
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aquí defino el contenido de mi aplicación usando mi tema personalizado.
        setContent {
            SapoCasaPrincesasTheme {
                Surface {
                    // Llamo a mi NavHost principal.
                    AppNavigation(navController = rememberNavController())


                }
            }
        }
    }
}
