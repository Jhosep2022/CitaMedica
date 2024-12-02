@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.appdoctor.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
fun AdminView(navController: NavHostController) {
    var selectedTab by remember { mutableStateOf("Doctor") }
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Fila de pestañas y búsqueda
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TabItem(
                icon = R.drawable.ic_doctor_blue,
                title = "Doctor",
                isSelected = selectedTab == "Doctor",
                onClick = { selectedTab = "Doctor" }
            )
            TabItem(
                icon = R.drawable.ic_paciente_blue,
                title = "Paciente",
                isSelected = selectedTab == "Paciente",
                onClick = { selectedTab = "Paciente" }
            )
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text(text = "Buscar...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFEFEFEF),
                    focusedIndicatorColor = PrimaryBlue,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }

        // Mostrar la lista según la pestaña seleccionada
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            if (selectedTab == "Doctor") {
                items(dummyDoctors) { doctor ->
                    CustomDoctorCard(
                        doctorName = doctor.name,
                        specialty = doctor.specialty,
                        imageRes = doctor.imageRes,
                        onClick = { navController.navigate("doctorInfo/${doctor.id}") }
                    )
                }
            } else {
                items(dummyPatients) { patient ->
                    CustomPatientCard(
                        patientName = patient.name,
                        age = patient.age,
                        type = patient.type,
                        imageRes = patient.imageRes
                    )
                }
            }
        }
    }
}


@Composable
fun TabItem(icon: Int, title: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable { onClick() }
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
fun CustomDoctorCard(doctorName: String, specialty: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }, // Navegar al hacer clic
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
                painter = painterResource(id = imageRes),
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
fun CustomPatientCard(patientName: String, age: Int, type: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE6EDFB)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = patientName, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                    Text(text = "Edad: $age", fontSize = 14.sp, color = Color.Gray)
                    Text(text = "Tipo: $type", fontSize = 14.sp, color = PrimaryBlue)
                }
            }
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Opciones",
                modifier = Modifier.size(24.dp),
                tint = Color.Gray
            )
        }
    }
}

