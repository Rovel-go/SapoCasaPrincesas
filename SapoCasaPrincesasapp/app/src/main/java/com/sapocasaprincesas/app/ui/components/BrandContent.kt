package com.sapocasaprincesas.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sapocasaprincesas.app.R

@Composable
fun BrandContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {


        Image(
            painter = painterResource(id = R.drawable.princesitareal),
            contentDescription = "Princesita real",
            modifier = Modifier
                .height(130.dp)
                .aspectRatio(0.75f)
        )

        Spacer(modifier = Modifier.width(16.dp))


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("S", color = Color(0xFF6BED0D), fontSize = 48.sp)
                Text("a", color = Color(0xFFE53935), fontSize = 48.sp)
                Text("p", color = Color(0xFFFFEB3B), fontSize = 48.sp)
                Text("o", color = Color(0xFFFF9800), fontSize = 48.sp)
            }

            Spacer(modifier = Modifier.height(4.dp))


            Text(
                text = "Casa de princesas!",
                color = Color.White,
                fontSize = 22.sp
            )
        }
    }
}








