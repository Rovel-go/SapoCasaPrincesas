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
fun VisionScreen() {

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
                text = "Visión",
                fontSize = 32.sp,
                color = Color(0xFFC9C2C2),
                lineHeight = 36.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Nuestra visión es consolidarnos como un referente en bienestar y estética femenina, reconocidos por la excelencia en el servicio, la innovación y el trato humano.",
                fontSize = 18.sp,
                lineHeight = 28.sp,
                color = Color(0xFFC9C2C2),
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 700.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Aspiramos a expandir nuestra propuesta, creando espacios donde cada mujer pueda sentirse segura, valorada y empoderada, celebrando su belleza real y única.",
                fontSize = 18.sp,
                lineHeight = 28.sp,
                color = Color(0xFFC9C2C2),
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 700.dp)
            )
        }
    }
}



