package com.sapocasaprincesas.app.ui.paginasInstitucionales

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactoScreen() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .widthIn(max = 900.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White.copy(alpha = 0.06f))
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Contáctanos",
                fontSize = 32.sp,
                color = Color(0xFFC9C2C2),
                lineHeight = 36.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Estamos aquí para ti. Si deseas agendar una cita, solicitar información o resolver cualquier inquietud, puedes comunicarte con nosotros a través de nuestros canales oficiales.",
                fontSize = 18.sp,
                lineHeight = 28.sp,
                color = Color(0xFFC9C2C2),
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 700.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "📍 Dirección: Calle 123 #45-67, Bogotá\n📞 Teléfono: +57 300 000 0000\n📧 Correo: contacto@casadeprincesas.com",
                fontSize = 18.sp,
                lineHeight = 28.sp,
                color = Color(0xFFC9C2C2),
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 700.dp)
            )
        }
    }
}


