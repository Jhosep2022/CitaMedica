@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.appdoctor.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appdoctor.R
import com.example.appdoctor.ui.components.Header
import com.example.appdoctor.ui.theme.PrimaryBlue
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DoctorScheduleScreen(
    navController: NavHostController,
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Header(title = "Horario", onBackClick = onBackClick)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Información del Doctor
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE6EDFB)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.doc1),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Cargo del Doctor",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "CDSA213141",
                        fontSize = 14.sp,
                        color = PrimaryBlue,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Dr. Alexander Bennett, Ph.D.",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Dermato-Genetics",
                        fontSize = 14.sp,
                        color = PrimaryBlue
                    )
                }
            }

            // Selección de día de trabajo
            Text(
                text = "Seleccionar Día de trabajo",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Por favor, seleccione el día de su jornada de trabajo para planificar sus consultas y compromisos. Recuerde que los días disponibles van de lunes a sábado, excluyendo los domingos.",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Botón para abrir el selector de fecha
            Button(
                onClick = { showDatePicker = true },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = selectedDate?.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                        ?: "Seleccionar Fecha",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón "Siguiente"
            Button(
                onClick = {
                    if (selectedDate != null) {
                        // Navegar a la siguiente pantalla con la fecha seleccionada como argumento
                        navController.navigate("registerSchedule/${selectedDate?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))}")
                    } else {
                        Toast.makeText(context, "Por favor selecciona una fecha", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Siguiente", color = Color.White, fontWeight = FontWeight.Bold)
            }


            // Muestra el selector de fecha
            if (showDatePicker) {
                DatePickerDialog(
                    onDateSelected = {
                        selectedDate = it
                        showDatePicker = false
                    },
                    onDismiss = { showDatePicker = false }
                )
            }
        }
    }
}

@Composable
fun DatePickerDialog(onDateSelected: (LocalDate) -> Unit, onDismiss: () -> Unit) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                datePickerState.selectedDateMillis?.let {
                    onDateSelected(
                        LocalDate.ofEpochDay(it / (24 * 60 * 60 * 1000))
                    )
                }
            }) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
