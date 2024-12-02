package com.example.appdoctor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.appdoctor.ui.main.Navigation
import com.example.appdoctor.ui.theme.AppDoctorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDoctorTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}
