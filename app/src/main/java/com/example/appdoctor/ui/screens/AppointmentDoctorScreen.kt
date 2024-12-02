@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.appdoctor.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.example.appdoctor.ui.components.Header
import com.example.appdoctor.ui.theme.PrimaryBlue

@Composable
fun AppointmentDoctorScreen(
    navController: NavHostController,
    onBackClick: () -> Unit = {}) {
    val appointmentsPending: List<Appointment> = listOf( // Asegúrate de usar el tipo correcto aquí
        Appointment(
            doctorName = "Dr. Olivia Turner, M.D.",
            specialty = "Dermato-Endocrinology",
            patientName = "Juan Gomez",
            date = "Sunday, 12 June",
            time = "9:30 AM - 10:00 AM",
            imageRes = R.drawable.doc1
        ),
        Appointment(
            doctorName = "Dr. Alexander Bennett, Ph.D.",
            specialty = "Dermato-Genetics",
            patientName = "Juan Gomez",
            date = "Friday, 20 June",
            time = "2:30 PM - 3:00 PM",
            imageRes = R.drawable.doc1
        )
    )

    Scaffold(
        topBar = {
            Header(title = "Tus Horarios", onBackClick = onBackClick)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("doctorSchedule")
                },
                containerColor = PrimaryBlue,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Horario")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF5F5F5)) // Fondo claro
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Lista de citas
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                // Iterar correctamente sobre la lista de citas
                items(appointmentsPending) { appointment ->
                    AppointmentCard(appointment)
                }
            }
        }
    }
}

@Composable
fun AppointmentCard(appointment: Appointment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE6EDFB)), // Fondo azul claro
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Encabezado con imagen y texto
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = appointment.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = appointment.doctorName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = appointment.specialty,
                        fontSize = 14.sp,
                        color = PrimaryBlue
                    )
                    Text(
                        text = "Paciente: ${appointment.patientName}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Detalles de fecha y hora
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "Calendario",
                    tint = PrimaryBlue,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = appointment.date ?: "",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = "Reloj",
                    tint = PrimaryBlue,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = appointment.time ?: "",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón "Detalle"
            Button(
                onClick = { /* Acción para ver detalles */ },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Detalle", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

