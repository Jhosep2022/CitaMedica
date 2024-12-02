package com.example.appdoctor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appdoctor.R
import com.example.appdoctor.ui.components.HomeBottomBar
import com.example.appdoctor.ui.components.HomeTopBar

@Composable
fun HomeScreen(
    role: String,
    navController: NavHostController,
    selectedTab: String,
    onTabChange: (String) -> Unit
) {
    Scaffold(
        topBar = { HomeTopBar(role = role, navController = navController) },
        bottomBar = {
            // Mostrar BottomBar solo si el usuario no es Invitado
            if (role != "Invitado") {
                HomeBottomBar(
                    selectedTab = selectedTab,
                    role = role, // Asegúrate de pasar el rol aquí
                    onHomeClick = { onTabChange("Home") },
                    onChatClick = {
                        if (role != "Administrador" || role != "Invitado" || role != "Paciente") {
                            onTabChange("Chat")
                            navController.navigate("appointmentsDoctor")
                        }
                    },
                    onProfileClick = {
                        onTabChange("Profile")
                        navController.navigate("profile")
                    },
                    onScheduleClick = {
                        onTabChange("Schedule")
                        navController.navigate("appointments")
                    },
                    onAddUserClick = {
                        if (role == "Administrador") {
                            onTabChange("addDoctor")
                            navController.navigate("addDoctor")
                        }
                    }
                )
            }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.TopCenter
            ) {
                when (role) {
                    "Administrador" -> AdminView(navController = navController)
                    "Doctor" -> DoctorView { doctorId ->
                        // Navegar a la pantalla de información del doctor
                        navController.navigate("doctorInfo/$doctorId")
                    }
                    "Paciente" -> PatientView(navController = navController) // Modificación aquí
                    "Invitado" -> GuestView(navController = navController)
                    else -> Text(
                        text = "Rol no reconocido",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    )
}


// Datos de prueba
data class Doctor(
    val id: String,
    val name: String,
    val specialty: String,
    val phone: String,
    val email: String,
    val origin: String,
    val imageRes: Int = R.drawable.doc2
)


data class Patient(
    val name: String,
    val age: Int,
    val type: String,
    val imageRes: Int
)


val dummyDoctors = listOf(
    Doctor(
        id = "1",
        name = "Dr. Alexander Bennett, Ph.D.",
        specialty = "Dermato-Genetics",
        phone = "54987656565",
        email = "alex.bennett@gmail.com",
        origin = "USA",
        imageRes = R.drawable.doc2
    ),
    Doctor(
        id = "2",
        name = "Dr. Sarah Connor",
        specialty = "Cardiology",
        phone = "54912345678",
        email = "sarah.connor@gmail.com",
        origin = "Canada",
        imageRes = R.drawable.doc2
    )
)


val dummyPatients = listOf(
    Patient("Olivia Turner", 24, "Regular", R.drawable.user1),
    Patient("Jaqueline Garcia", 30, "VIP", R.drawable.user2)
)


