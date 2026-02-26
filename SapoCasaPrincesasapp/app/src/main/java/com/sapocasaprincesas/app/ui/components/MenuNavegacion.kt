package com.sapocasaprincesas.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MenuNavegacion(
    navController: NavController,
    items: List<NavItem>,
    disabled: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        items.forEach { item ->
            Box(
                modifier = Modifier
                    .background(
                        color = Color.White.copy(alpha = if (disabled) 0.05f else 0.12f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable(enabled = !disabled) {
                        navController.navigate(item.route)
                    }
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.label,
                    color = Color(0xFF12ABE7),
                    fontSize = 16.sp
                )
            }
        }
    }
}

