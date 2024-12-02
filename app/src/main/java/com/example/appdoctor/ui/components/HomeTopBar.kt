@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.appdoctor.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appdoctor.R
import com.example.appdoctor.ui.theme.PrimaryBlue

@Composable
fun HomeTopBar(role: String, navController: NavHostController, isMale: Boolean = true) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Imagen de perfil (de los recursos `drawable`)
                    Image(
                        painter = painterResource(
                            id = if (isMale) R.drawable.user1 else R.drawable.user2
                        ),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    // Saludo y rol
                    Column {
                        Text(
                            text = "Hi, Welcome Back",
                            fontSize = 16.sp,
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = role,
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Botón de configuración o logout dependiendo del rol
                IconButton(
                    onClick = {
                        if (role == "Invitado") {
                            // Navegar al login si es invitado
                            navController.navigate("login") {
                                popUpTo("home") { inclusive = true }
                            }
                        } else {
                            // Navegar a la configuración para otros roles
                            navController.navigate("settings")
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (role == "Invitado") Icons.Default.ExitToApp else Icons.Default.Settings,
                        contentDescription = if (role == "Invitado") "Logout Icon" else "Settings Icon",
                        tint = PrimaryBlue
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}
