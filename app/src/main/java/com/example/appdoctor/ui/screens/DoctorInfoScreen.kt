@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.appdoctor.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdoctor.R
import com.example.appdoctor.ui.components.Header
import com.example.appdoctor.ui.theme.PrimaryBlue

@Composable
fun DoctorInfoScreen(doctorId: String, onBackClick: () -> Unit) {
    // Busca el doctor en la lista usando el ID proporcionado
    val doctor = dummyDoctors.find { it.id == doctorId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Cabecera reutilizada
        Header(title = "Doctor Info", onBackClick = onBackClick)

        Spacer(modifier = Modifier.height(16.dp))

        // Verifica si el doctor existe
        if (doctor != null) {
            // Card principal
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE6EDFB))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Imagen circular del doctor
                    Image(
                        painter = painterResource(doctor.imageRes),
                        contentDescription = "Doctor Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape) // Corrigiendo el uso de clip
                            .background(Color.Gray, CircleShape)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Cargo y código del doctor en un estilo más claro
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Cargo del Doctor",
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = PrimaryBlue,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "CDS-A213141",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .background(
                                    color = Color(0xFF1E88E5),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 2.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Nombre del doctor
                    Text(
                        text = doctor.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = PrimaryBlue,
                        textAlign = TextAlign.Center
                    )

                    // Especialidad del doctor
                    Text(
                        text = doctor.specialty,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Información de contacto
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                InfoRow(label = "Teléfono", value = doctor.phone)
                Spacer(modifier = Modifier.height(8.dp))
                InfoRow(label = "Email", value = doctor.email)
                Spacer(modifier = Modifier.height(8.dp))
                InfoRow(label = "Origen", value = doctor.origin)
            }
        } else {
            // Mensaje de error si el doctor no existe
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Doctor no encontrado",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = PrimaryBlue
        )
        Text(
            text = value,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}
