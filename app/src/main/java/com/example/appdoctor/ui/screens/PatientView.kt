@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.appdoctor.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

// Vista del Paciente
@Composable
fun PatientView(navController: NavHostController) {
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
                TabItem3(
                    icon = R.drawable.ic_doctor_blue,
                    title = "Doctores",
                    isSelected = selectedTab == "Doctores",
                    onClick = { selectedTab = "Doctores" }
                )
                TabItem3(
                    icon = R.drawable.ic_calendar,
                    title = "Horarios",
                    isSelected = selectedTab == "Horarios",
                    onClick = { selectedTab = "Horarios" }
                )
                TabItem3(
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
            "Doctores" -> DoctorsTab2 { doctorId ->
                navController.navigate("doctorInfo/$doctorId")
            }
            "Horarios" -> SchedulesTab2(navController)
            "Especialidad" -> SpecialtiesTab2()
        }
    }
}

@Composable
fun TabItem3(icon: Int, title: String, isSelected: Boolean, onClick: () -> Unit) {
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
fun DoctorsTab2(onDoctorClick: (String) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(dummyDoctors) { doctor ->
            DoctorCard2(
                doctorName = doctor.name,
                specialty = doctor.specialty,
                imageRes = R.drawable.hombre,
                onClick = { onDoctorClick(doctor.id) }
            )
        }
    }
}

@Composable
fun SchedulesTab2(navController: NavHostController) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(dummySchedules) { schedule ->
            ScheduleCard2(schedule = schedule, navController = navController)
        }
    }
}

@Composable
fun SpecialtiesTab2() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(dummySpecialties) { specialty ->
            SpecialtyCard2(
                specialtyName = specialty.name,
                description = specialty.description
            )
        }
    }
}

@Composable
fun DoctorCard2(
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
fun ScheduleCard2(schedule: Schedule, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                // Navegar a la pantalla de detalles del horario
                navController.navigate(
                    "scheduleDetails/${schedule.doctorName}/${schedule.specialty}/${schedule.date}/${schedule.time}"
                )
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE6EDFB)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.hombre),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
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
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
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
                        painter = painterResource(id = R.drawable.ic_clock),
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
fun SpecialtyCard2(specialtyName: String, description: String) {
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