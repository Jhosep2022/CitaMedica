@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.appdoctor.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdoctor.R
import com.example.appdoctor.ui.components.Header
import com.example.appdoctor.ui.theme.PrimaryBlue

@Composable
fun AppointmentScreen(onBackClick: () -> Unit = {}) {
    var selectedTab by remember { mutableStateOf("Atendido") }
    val appointmentsComplete = listOf(
        Appointment(
            doctorName = "Dr. Olivia Turner, M.D.",
            specialty = "Dermato-Endocrinology",
            patientName = "Juan Gomez",
            imageRes = R.drawable.hombre
        ),
        Appointment(
            doctorName = "Dr. Alexander Bennett, Ph.D.",
            specialty = "Dermato-Genetics",
            patientName = "Juan Gomez",
            imageRes = R.drawable.hombre
        )
    )

    val appointmentsPending = listOf(
        Appointment(
            doctorName = "Dr. Olivia Turner, M.D.",
            specialty = "Dermato-Endocrinology",
            patientName = "Juan Gomez",
            date = "Sunday, 12 June",
            time = "9:30 AM - 10:00 AM",
            imageRes = R.drawable.hombre
        ),
        Appointment(
            doctorName = "Dr. Alexander Bennett, Ph.D.",
            specialty = "Dermato-Genetics",
            patientName = "Juan Gomez",
            date = "Friday, 20 June",
            time = "2:30 PM - 3:00 PM",
            imageRes = R.drawable.hombre
        )
    )

    val appointmentsCancelled = appointmentsComplete

    Scaffold(
        topBar = {
            Header(title = "Todas Las Citas", onBackClick = onBackClick)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TabButton("Atendido", selectedTab) { selectedTab = "Atendido" }
                TabButton("Pendiente", selectedTab) { selectedTab = "Pendiente" }
                TabButton("Cancelado", selectedTab) { selectedTab = "Cancelado" }
            }

            // Content
            when (selectedTab) {
                "Atendido" -> AppointmentList(appointments = appointmentsComplete, type = "Atendido")
                "Pendiente" -> AppointmentList(appointments = appointmentsPending, type = "Pendiente")
                "Cancelado" -> AppointmentList(appointments = appointmentsCancelled, type = "Cancelado")
            }
        }
    }
}

@Composable
fun TabButton(title: String, selectedTab: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            contentColor = if (title == selectedTab) Color.White else PrimaryBlue,
            containerColor = if (title == selectedTab) PrimaryBlue else Color(0xFFEFEFEF)
        ),
        modifier = Modifier
            .padding(4.dp)
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AppointmentList(appointments: List<Appointment>, type: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        appointments.forEach { appointment ->
            when (type) {
                "Atendido" -> AppointmentCardAtendido(appointment)
                "Pendiente" -> AppointmentCardPendiente(appointment)
                "Cancelado" -> AppointmentCardCancelado(appointment)
            }
        }
    }
}


@Composable
fun AppointmentCardAtendido(appointment: Appointment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = appointment.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { /* Acción para reprogramar */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Reprogramar", color = PrimaryBlue)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { /* Acción para ver detalle */ },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Ver Detalle", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun AppointmentCardPendiente(appointment: Appointment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = appointment.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = appointment.date ?: "",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = appointment.time ?: "",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                IconButton(onClick = { /* Acción aceptar */ }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Aceptar",
                        tint = PrimaryBlue
                    )
                }
                IconButton(onClick = { /* Acción rechazar */ }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Rechazar",
                        tint = Color.Red
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { /* Acción detalle */ },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Detalle", color = Color.White)
            }
        }
    }
}

@Composable
fun AppointmentCardCancelado(appointment: Appointment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = appointment.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
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
            Button(
                onClick = { /* Acción detalle */ },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ver Detalle", color = Color.White)
            }
        }
    }
}


data class Appointment(
    val doctorName: String,
    val specialty: String,
    val patientName: String,
    val date: String? = null,
    val time: String? = null,
    val imageRes: Int
)
