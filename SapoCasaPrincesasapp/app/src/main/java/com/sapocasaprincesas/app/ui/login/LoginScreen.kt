package com.sapocasaprincesas.app.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sapocasaprincesas.app.navigation.Routes
import com.sapocasaprincesas.app.ui.components.FondoInstitucional

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {

    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    val estado by viewModel.estado.collectAsState()

    LaunchedEffect(estado) {
        if (estado is LoginEstado.Exito) {
            usuario = ""
            contrasena = ""

            navController.navigate(Routes.Home.route)
        }
    }

    // Colores del frontend
    val azulFront = Color(0xFF00BFFF)
    val verdeFront = Color(0xFF00FF7F)

    FondoInstitucional {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            // Campo Usuario
            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Usuario", color = Color.White) },
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

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Contraseña
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña", color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

            // Olvidé mi contraseña
            Text(
                text = "Olvidé mi contraseña",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp)
                    .clickable {
                        navController.navigate(Routes.Recuperar.route)
                    }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón azul estilo frontend
            Button(
                onClick = {
                    viewModel.login(usuario, contrasena)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = azulFront
                )
            ) {
                Text("Iniciar sesión >>", color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // No tengo cuenta
            Text(
                text = "No tengo cuenta",
                color = Color.White
            )

            // ¡Quiero registrarme!
            Text(
                text = "¡Quiero registrarme!",
                color = verdeFront,
                modifier = Modifier.clickable {
                    navController.navigate(Routes.Registro.route)
                }
            )

            if (estado is LoginEstado.Error) {
                Text(
                    text = (estado as LoginEstado.Error).mensaje,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}











