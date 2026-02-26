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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuienesSomosScreen() {

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
                text = "Quiénes Somos",
                fontSize = 32.sp,
                color = Color(0xFFC9C2C2),
                lineHeight = 36.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "En Casa de Princesas somos un espacio creado para celebrar la belleza, la autenticidad y la confianza de cada mujer. Nacimos con la visión de ofrecer experiencias que van más allá de un servicio estético: buscamos transformar momentos cotidianos en instantes memorables.",
                fontSize = 18.sp,
                lineHeight = 28.sp,
                color = Color(0xFFC9C2C2),
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 700.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Nuestro equipo está conformado por profesionales apasionados por el bienestar, la imagen personal y el trato humano. Creemos en la importancia de escuchar, acompañar y brindar un servicio cálido, respetuoso y personalizado.",
                fontSize = 18.sp,
                lineHeight = 28.sp,
                color = Color(0xFFC9C2C2),
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 700.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Casa de Princesas es un lugar donde cada cliente es tratada con dedicación, cuidado y excelencia. Aquí, cada detalle importa y cada visita es una oportunidad para resaltar la belleza real que habita en cada mujer.",
                fontSize = 18.sp,
                lineHeight = 28.sp,
                color = Color(0xFFC9C2C2),
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 700.dp)
            )
        }
    }
}



