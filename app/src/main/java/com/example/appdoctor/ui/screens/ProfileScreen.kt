package com.example.appdoctor.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appdoctor.ui.components.Header
import com.example.appdoctor.ui.theme.PrimaryBlue
import androidx.compose.material3.*

@Composable
fun ProfileScreen(navController: NavHostController, onBackClick: () -> Unit = {}) {
    var showLogoutDialog by remember { mutableStateOf(false) } // Estado para mostrar el diálogo

    Scaffold(
        topBar = {
            Header(title = "Mi Perfil", onBackClick = onBackClick)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Imagen de perfil con botón de editar
            Box(contentAlignment = Alignment.BottomEnd, modifier = Modifier.padding(top = 16.dp)) {
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape)
                        .background(PrimaryBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile Picture",
                        tint = Color.White,
                        modifier = Modifier.size(80.dp)
                    )
                }
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Profile Picture",
                    tint = PrimaryBlue,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp)
                )
            }

            // Nombre del usuario
            Text(
                text = "John Doe",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Opciones del perfil
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileOption(
                    icon = Icons.Default.Person,
                    title = "Perfil",
                    onClick = { navController.navigate("edit_profile") }
                )
                ProfileOption(
                    icon = Icons.Default.Settings,
                    title = "Settings",
                    onClick = { navController.navigate("settings") }
                )
                ProfileOption(
                    icon = Icons.Default.Help,
                    title = "Help",
                    onClick = { /* Acción Help */ }
                )
                ProfileOption(
                    icon = Icons.Default.Person,
                    title = "Rol",
                    onClick = { /* Acción Rol */ }
                )
                ProfileOption(
                    icon = Icons.Default.ExitToApp,
                    title = "Logout",
                    onClick = { showLogoutDialog = true } // Mostrar el cuadro de diálogo
                )
            }
        }
    }

    // Cuadro de diálogo para confirmar Logout
    if (showLogoutDialog) {
        LogoutConfirmationDialog(
            onConfirm = {
                showLogoutDialog = false
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true }
                }
            },
            onCancel = { showLogoutDialog = false }
        )
    }
}

@Composable
fun ProfileOption(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icono de la opción
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = PrimaryBlue,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 16.dp)
        )

        // Título de la opción
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun LogoutConfirmationDialog(onConfirm: () -> Unit, onCancel: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onCancel() },
        title = {
            Text(
                text = "Cerrar Sesión",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue
            )
        },
        text = {
            Text(
                text = "¿Estás segura de que quieres cerrar sesión?",
                fontSize = 16.sp,
                color = Color.Black
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
            ) {
                Text(text = "Cerrar Sesión", color = Color.White)
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onCancel) {
                Text(text = "Cancelar", color = PrimaryBlue)
            }
        },
        containerColor = Color.White,
        shape = RoundedCornerShape(16.dp)
    )
}
