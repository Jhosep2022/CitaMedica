package com.example.appdoctor.ui.main

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appdoctor.ui.screens.AddDoctorScreen
import com.example.appdoctor.ui.screens.AppointmentDoctorScreen
import com.example.appdoctor.ui.screens.AppointmentScreen
import com.example.appdoctor.ui.screens.ChangePasswordScreen
import com.example.appdoctor.ui.screens.DoctorInfoScreen
import com.example.appdoctor.ui.screens.DoctorScheduleScreen
import com.example.appdoctor.ui.screens.EditProfileScreen
import com.example.appdoctor.ui.screens.HomeScreen
import com.example.appdoctor.ui.screens.LoginScreen
import com.example.appdoctor.ui.screens.OnboardingScreen
import com.example.appdoctor.ui.screens.ProfileScreen
import com.example.appdoctor.ui.screens.RegisterScheduleScreen
import com.example.appdoctor.ui.screens.RegisterScreen
import com.example.appdoctor.ui.screens.ScheduleDetailsScreen
import com.example.appdoctor.ui.screens.SettingsScreen
import com.example.appdoctor.ui.screens.WelcomeScreen

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    var selectedTab by remember { mutableStateOf("Home") }

    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") { OnboardingScreen(navController) }
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("settings") { SettingsScreen(navController = navController) }
        composable("addDoctor") { AddDoctorScreen(navController = navController) }
        composable("appointmentsDoctor") {
            AppointmentDoctorScreen(navController = navController,onBackClick = { navController.popBackStack() })
        }
        composable("home/{role}") { backStackEntry ->
            val role = backStackEntry.arguments?.getString("role") ?: "Invitado"
            HomeScreen(
                role = role,
                navController = navController,
                selectedTab = selectedTab,
                onTabChange = { newTab -> selectedTab = newTab }
            )
        }
        composable("profile") {
            ProfileScreen(
                navController = navController,
                onBackClick = {
                    selectedTab = "Home"
                    navController.popBackStack()
                }
            )
        }
        composable("edit_profile") {
            EditProfileScreen(
                onBackClick = { navController.popBackStack() },
                onUpdateClick = { /* Acción para actualizar perfil */ }
            )
        }
        composable("changePassword") {
            ChangePasswordScreen(onBackClick = { navController.popBackStack() })
        }
        composable("appointments") {
            AppointmentScreen(onBackClick = { navController.popBackStack() })
        }

        // Nueva ruta para la pantalla DoctorInfoScreen
        composable(
            route = "doctorInfo/{doctorId}",
            arguments = listOf(navArgument("doctorId") { type = NavType.StringType })
        ) { backStackEntry ->
            val doctorId = backStackEntry.arguments?.getString("doctorId")
            DoctorInfoScreen(
                doctorId = doctorId ?: "",
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("doctorSchedule") {
            DoctorScheduleScreen(
                navController = navController, // Pasamos el navController aquí
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            route = "registerSchedule/{selectedDate}",
            arguments = listOf(navArgument("selectedDate") { type = NavType.StringType })
        ) { backStackEntry ->
            val selectedDate = backStackEntry.arguments?.getString("selectedDate") ?: ""
            RegisterScheduleScreen(
                selectedDate = selectedDate,
                onBackClick = { navController.popBackStack() },
                onRegisterClick = { startHour, endHour, additionalParam ->
                    // Lógica adicional para registrar
                }
            )
        }

        composable(
            route = "scheduleDetails/{doctorName}/{specialty}/{date}/{time}",
            arguments = listOf(
                navArgument("doctorName") { type = NavType.StringType },
                navArgument("specialty") { type = NavType.StringType },
                navArgument("date") { type = NavType.StringType },
                navArgument("time") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            ScheduleDetailsScreen(
                navController = navController,
                doctorName = backStackEntry.arguments?.getString("doctorName") ?: "",
                specialty = backStackEntry.arguments?.getString("specialty") ?: "",
                date = backStackEntry.arguments?.getString("date") ?: "",
                time = backStackEntry.arguments?.getString("time") ?: ""
            )
        }


    }
}


