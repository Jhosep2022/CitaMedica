@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.appdoctor.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdoctor.ui.theme.PrimaryBlue

@Composable
fun RegisterScheduleScreen(
    selectedDate: String, // Parámetro para recibir la fecha seleccionada en formato "dd-MM-yyyy"
    onBackClick: () -> Unit = {},
    onRegisterClick: (String, String, String) -> Unit = { _, _, _ -> }
) {
    var selectedTurn by remember { mutableStateOf("Mañana") }
    var selectedStartHour by remember { mutableStateOf("") }
    var selectedEndHour by remember { mutableStateOf("") }
    var description by remember { mutableStateOf(TextFieldValue("")) }

    // Aseguramos que la fecha está en el formato correcto "dd-MM-yyyy"
    val parts = selectedDate.split("-")
    val day = parts[0].toInt() // Día
    val month = parts[1] // Mes
    val year = parts[2] // Año

    // Calculamos los días que se mostrarán
    val daysToShow = (day - 2..day + 2).toList() // Dos días antes y dos después de la fecha seleccionada

    val morningHours = listOf("8:00 AM", "8:30 AM", "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM")
    val afternoonHours = listOf("2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM")
    val availableHours = if (selectedTurn == "Mañana") morningHours else afternoonHours

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Dr. Alexander Bannett",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Atrás",
                            tint = PrimaryBlue
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .background(Color(0xFFF5F5F5)),
            horizontalAlignment = Alignment.Start
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))

                // Cabecera de Fecha
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    daysToShow.forEach { dayItem ->
                        Box(
                            modifier = Modifier
                                .size(if (dayItem == day) 50.dp else 40.dp)
                                .background(
                                    if (dayItem == day) PrimaryBlue else Color(0xFFE6EDFB),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = dayItem.toString(),
                                color = if (dayItem == day) Color.White else PrimaryBlue,
                                fontWeight = FontWeight.Bold,
                                fontSize = if (dayItem == day) 16.sp else 14.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Turno
                Text("Turno", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(
                        onClick = { selectedTurn = "Mañana" },
                        colors = ButtonDefaults.buttonColors(containerColor = if (selectedTurn == "Mañana") PrimaryBlue else Color(0xFFE6EDFB)),
                        modifier = Modifier.weight(1f).padding(end = 8.dp)
                    ) {
                        Text("Mañana", color = if (selectedTurn == "Mañana") Color.White else PrimaryBlue)
                    }
                    Button(
                        onClick = { selectedTurn = "Tarde" },
                        colors = ButtonDefaults.buttonColors(containerColor = if (selectedTurn == "Tarde") PrimaryBlue else Color(0xFFE6EDFB)),
                        modifier = Modifier.weight(1f).padding(start = 8.dp)
                    ) {
                        Text("Tarde", color = if (selectedTurn == "Tarde") Color.White else PrimaryBlue)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Horarios Disponibles
                Text("Horarios Disponibles", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                Column {
                    availableHours.chunked(3).forEach { row ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            row.forEach { hour ->
                                Button(
                                    onClick = {
                                        if (selectedStartHour.isEmpty()) {
                                            selectedStartHour = hour
                                        } else {
                                            selectedEndHour = hour
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = if (hour == selectedStartHour || hour == selectedEndHour) PrimaryBlue else Color(0xFFE6EDFB)),
                                    modifier = Modifier.padding(4.dp)
                                ) {
                                    Text(
                                        hour,
                                        color = if (hour == selectedStartHour || hour == selectedEndHour) Color.White else PrimaryBlue,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Formulario
                Text("Fecha Seleccionada", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                TextField(
                    value = "$day/$month/$year",
                    onValueChange = {},
                    readOnly = true,
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Horario Inicio", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                TextField(
                    value = selectedStartHour,
                    onValueChange = {},
                    readOnly = true,
                    placeholder = { Text("Seleccionar inicio") },
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Horario Fin", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                TextField(
                    value = selectedEndHour,
                    onValueChange = {},
                    readOnly = true,
                    placeholder = { Text("Seleccionar fin") },
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Descripción", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                BasicTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 16.sp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón Registrar
                Button(
                    onClick = {
                        if (selectedStartHour.isNotEmpty() && selectedEndHour.isNotEmpty()) {
                            onRegisterClick(selectedStartHour, selectedEndHour, description.text)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrar", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
