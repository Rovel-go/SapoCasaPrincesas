package com.sapocasaprincesas.app.ui.registro

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
import com.sapocasaprincesas.app.navigation.Routes
import com.sapocasaprincesas.app.ui.components.FondoInstitucional
import com.sapocasaprincesas.app.data.auth.RegistroResponse
import com.sapocasaprincesas.app.data.auth.registro.RegistroViewModel

@Composable
fun RegistroScreen(navController: NavController) {

    val viewModel: RegistroViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    // ⭐ AHORA SÍ: estado REAL del ViewModel
    val estado = viewModel.estado.value

    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }

    val azulFront = Color(0xFF00BFFF)
    val verdeFront = Color(0xFF00FF7F)


    // LIMPIAR CAMPOS DESPUÉS DE REGISTRO EXITOSO

    LaunchedEffect(estado.resultado) {
        if (estado.resultado is RegistroResponse.Exito) {

            nombre = ""
            apellidos = ""
            correo = ""
            contrasena = ""
            confirmarContrasena = ""

            navController.navigate(Routes.Login.route)
        }
    }


    // MOSTRAR ERROR

    if (estado.resultado is RegistroResponse.Error) {
        val mensaje = (estado.resultado as RegistroResponse.Error).mensaje

        Text(
            text = mensaje,
            color = Color.Red,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }

    FondoInstitucional {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            // Nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre", color = Color.White) },
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

            // Apellidos
            OutlinedTextField(
                value = apellidos,
                onValueChange = { apellidos = it },
                label = { Text("Apellidos", color = Color.White) },
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

            // Correo
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("E-mail", color = Color.White) },
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

            Spacer(modifier = Modifier.height(16.dp))

            // Contraseña
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña", color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
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

            // Confirmar contraseña
            OutlinedTextField(
                value = confirmarContrasena,
                onValueChange = { confirmarContrasena = it },
                label = { Text("Confirmar contraseña", color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
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


            // BOTÓN REGISTRAR

            Button(
                onClick = {

                    if (contrasena != confirmarContrasena) {
                        viewModel.mostrarError("Las contraseñas no coinciden")
                        return@Button
                    }

                    viewModel.registrar(
                        nombre,
                        apellidos,
                        correo,
                        contrasena
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = azulFront
                )
            ) {
                if (estado.cargando) {
                    CircularProgressIndicator(color = Color.White)
                } else {
                    Text("Crear cuenta >>", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "¿Ya tienes cuenta? Inicia sesión",
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



