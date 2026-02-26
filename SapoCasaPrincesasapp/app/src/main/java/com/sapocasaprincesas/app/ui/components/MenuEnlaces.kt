package com.sapocasaprincesas.app.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sapocasaprincesas.app.R
import com.sapocasaprincesas.app.navigation.Routes
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement

@Composable
fun MenuEnlaces(
    showHome: Boolean,
    showSocialIcons: Boolean,
    showLogout: Boolean,
    navController: NavController,
    onLogout: () -> Unit
) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        color = Color.White.copy(alpha = 0.04f),   // glassmorphism
        shadowElevation = 12.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {


            if (showHome) {
                IconButton(onClick = {
                    navController.navigate(Routes.Home.route)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = "Home",
                        tint = Color.Unspecified   // ← usa el color del drawable
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp))
            }


            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (showSocialIcons) {

                    IconButton(onClick = {
                        context.startActivity(
                            Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com"))
                        )
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_facebook),
                            contentDescription = "Facebook",
                            tint = Color.Unspecified
                        )
                    }

                    IconButton(onClick = {
                        context.startActivity(
                            Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com"))
                        )
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_instagram),
                            contentDescription = "Instagram",
                            tint = Color.Unspecified
                        )
                    }

                    IconButton(onClick = {
                        context.startActivity(
                            Intent(Intent.ACTION_VIEW, Uri.parse("https://tiktok.com"))
                        )
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_tiktok),
                            contentDescription = "TikTok",
                            tint = Color.Unspecified
                        )
                    }
                }
            }


            if (showLogout) {
                IconButton(onClick = onLogout) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = "Salir",
                        tint = Color.Unspecified
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp))
            }
        }
    }
}

