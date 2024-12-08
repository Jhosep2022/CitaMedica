package com.example.appdoctor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.appdoctor.ui.main.Navigation
import com.example.appdoctor.ui.theme.AppDoctorTheme
import com.example.appdoctor.ui.viewmodel.PersonaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDoctorTheme {
                val navController = rememberNavController()
                val personaViewModel: PersonaViewModel = viewModel()
                Navigation(navController = navController, personaViewModel = personaViewModel)
            }
        }
    }
}
