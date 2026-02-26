package com.sapocasaprincesas.app.ui.recuperar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sapocasaprincesas.app.navigation.Routes
import com.sapocasaprincesas.app.ui.components.FondoInstitucional
import com.sapocasaprincesas.app.data.auth.RecuperarResponse
import com.sapocasaprincesas.app.data.auth.recuperar.RecuperarViewModel

@Composable
fun RecuperarContrasenaScreen(navController: NavController) {

    val viewModel: RecuperarViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val estado = viewModel.estado

    var correo by remember { mutableStateOf("") }

    // Colores del frontend
    val azulFront = Color(0xFF00BFFF)
    val verdeFront = Color(0xFF00FF7F)


    // LIMPIAR CAMPO DESPUÉS DE ÉXITO

    LaunchedEffect(estado.resultado) {
        if (estado.resultado is RecuperarResponse.Exito) {

            // LIMPIAR CAMPO
            correo = ""

            // No hay navegacion inmediata para que el usuario vea la contraseña temporal
            // Navegación ocurre cuando el usuario presiona el botón
        }
    }

    FondoInstitucional {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            // Título
            Text(
                text = "Recuperar contraseña",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Campo de correo
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo electrónico", color = Color.White) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = azulFront,
                    unfocusedBorderColor = azulFront,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(32.dp))


            // BOTÓN RECUPERAR

            Button(
                onClick = {
                    if (correo.isBlank()) {
                        viewModel.mostrarError("El correo no puede estar vacío")
                        return@Button
                    }

                    viewModel.recuperar(correo)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = azulFront
                )
            ) {
                if (estado.cargando) {
                    CircularProgressIndicator(color = Color.White)
                } else {
                    Text("Enviar instrucciones >>", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            // MOSTRAR ERROR

            if (estado.resultado is RecuperarResponse.Error) {
                Text(
                    text = (estado.resultado as RecuperarResponse.Error).mensaje,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }


            // MOSTRAR CONTRASEÑA TEMPORAL

            if (estado.resultado is RecuperarResponse.Exito) {

                val temp = (estado.resultado as RecuperarResponse.Exito).contrasenaTemporal

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Tu contraseña temporal es:",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = temp,
                    color = Color.Yellow,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        navController.navigate(Routes.Login.route)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = azulFront)
                ) {
                    Text("Ir al inicio de sesión", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Enlace para volver al login
            Text(
                text = "Volver al inicio de sesión",
                color = verdeFront,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        navController.navigate(Routes.Login.route)
                    }
            )
        }
    }
}

