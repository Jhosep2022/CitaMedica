@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.appdoctor.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdoctor.ui.theme.PrimaryBlue

@Composable
fun Header(title: String, onBackClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            // Botón de regreso
            Text(
                text = "<",
                fontSize = 24.sp,
                color = PrimaryBlue,
                modifier = Modifier
                    .clickable { onBackClick() }
                    .padding(start = 8.dp)
            )
        },
        title = {
            // Título centrado
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = androidx.compose.ui.Alignment.Center // Cambiado
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}
