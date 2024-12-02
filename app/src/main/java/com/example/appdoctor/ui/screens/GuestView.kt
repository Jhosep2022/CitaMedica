@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.appdoctor.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Search
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
import androidx.navigation.NavHostController
import com.example.appdoctor.R
import com.example.appdoctor.ui.theme.PrimaryBlue

@Composable
fun GuestView(navController: NavHostController) {
    var selectedTab by remember { mutableStateOf("Doctores") }
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Cabecera
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Pestañas
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.weight(3f)
            ) {
                TabItem1(
                    icon = R.drawable.ic_doctor_blue,
                    title = "Doctores",
                    isSelected = selectedTab == "Doctores",
                    onClick = { selectedTab = "Doctores" }
                )
                TabItem1(
                    icon = R.drawable.ic_calendar,
                    title = "Horarios",
                    isSelected = selectedTab == "Horarios",
                    onClick = { selectedTab = "Horarios" }
                )
                TabItem1(
                    icon = R.drawable.ic_specialty,
                    title = "Especialidad",
                    isSelected = selectedTab == "Especialidad",
                    onClick = { selectedTab = "Especialidad" }
                )
            }

            // Buscador
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text(text = "Buscar...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFE6EDFB),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = PrimaryBlue,
                    unfocusedLabelColor = Color.Gray
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Contenido de las pestañas
        when (selectedTab) {
            "Doctores" -> DoctorsTab { doctorId ->
                navController.navigate("doctorInfo/$doctorId")
            }
            "Horarios" -> SchedulesTab()
            "Especialidad" -> SpecialtiesTab()
        }
    }
}

@Composable
fun TabItem1(icon: Int, title: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 8.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = title,
            modifier = Modifier
                .size(24.dp)
                .padding(bottom = 4.dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) PrimaryBlue else Color.Gray
        )
    }
}

@Composable
fun DoctorsTab(onDoctorClick: (String) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(dummyDoctors) { doctor ->
            DoctorCard(
                doctorName = doctor.name,
                specialty = doctor.specialty,
                imageRes = R.drawable.hombre,
                onClick = { onDoctorClick(doctor.id) } // Pasar el ID del doctor al callback
            )
        }
    }
}


@Composable
fun SchedulesTab() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(dummySchedules) { schedule ->
            ScheduleCard(schedule = schedule)
        }
    }
}

@Composable
fun SpecialtiesTab() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(dummySpecialties) { specialty ->
            SpecialtyCard(
                specialtyName = specialty.name,
                description = specialty.description
            )
        }
    }
}

@Composable
fun DoctorCard(
    doctorName: String,
    specialty: String,
    imageRes: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .clickable { onClick() }, // Clic para navegar
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE6EDFB)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del doctor
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Información del doctor
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = doctorName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue
                )
                Text(
                    text = specialty,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ScheduleCard(schedule: Schedule) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE6EDFB)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del doctor
            Image(
                painter = painterResource(id = R.drawable.doc1), // Cambia a la imagen correspondiente
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Información principal
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = schedule.doctorName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue
                )
                Text(
                    text = schedule.specialty,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Información de fecha y hora
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar), // Ícono de calendario
                        contentDescription = null,
                        tint = PrimaryBlue,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = schedule.date,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clock), // Ícono de reloj
                        contentDescription = null,
                        tint = PrimaryBlue,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = schedule.time,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun SpecialtyCard(specialtyName: String, description: String) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE6EDFB)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_specialty),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = PrimaryBlue
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = specialtyName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = "Expandir",
                    tint = PrimaryBlue
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = description, fontSize = 14.sp, color = Color.Gray)
                }
            }
        }
    }
}

// Datos de ejemplo
data class Specialty(val name: String, val description: String)

data class Schedule(
    val doctorName: String,
    val specialty: String,
    val date: String,
    val time: String
)

// Ejemplo
val dummySchedules = listOf(
    Schedule(
        doctorName = "Dr. Olivia Turner, M.D.",
        specialty = "Dermato-Endocrinology",
        date = "Sunday, 12 June",
        time = "9:30 AM - 10:00 AM"
    ),
    Schedule(
        doctorName = "Dr. Alexander Bennett, Ph.D.",
        specialty = "Dermato-Genetics",
        date = "Friday, 20 June",
        time = "2:30 PM - 3:00 PM"
    )
)


val dummySpecialties = listOf(
    Specialty("Dermatología", "Descripción de dermatología"),
    Specialty("Cardiología", "Descripción de cardiología")
)

