package com.example.appdoctor.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appdoctor.ui.components.Header
import com.example.appdoctor.ui.theme.PrimaryBlue
import androidx.compose.material3.*
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun SettingsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Header(
                title = "Settings",
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SettingsOption(
                icon = Icons.Default.Key,
                title = "Cambiar Contraseña",
                onClick = { navController.navigate("changePassword") }
            )
            SettingsOption(
                icon = Icons.Default.Person,
                title = "Eliminar Cuenta",
                onClick = { /* Acción para eliminar cuenta */ }
            )
            SettingsOption(
                icon = Icons.Default.Add,
                title = "Agregar Doctor",
                onClick = { navController.navigate("addDoctor") } // Navegar a la pantalla de agregar doctor
            )
        }
    }
}

@Composable
fun SettingsOption(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = PrimaryBlue, // Color azul para el ícono
            modifier = Modifier
                .size(40.dp)
                .padding(end = 16.dp)
        )
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = PrimaryBlue,
            modifier = Modifier.size(24.dp)
        )
    }
}
