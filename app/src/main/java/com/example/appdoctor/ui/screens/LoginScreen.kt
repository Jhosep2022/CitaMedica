package com.example.appdoctor.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appdoctor.ui.components.Header
import com.example.appdoctor.ui.theme.PrimaryBlue
import com.example.appdoctor.ui.theme.LightBlue

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Header(
                title = "Iniciar Sesión",
                onBackClick = { navController.popBackStack() }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Email",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("example@example.com") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = LightBlue,
                            unfocusedContainerColor = LightBlue,
                            focusedIndicatorColor = PrimaryBlue,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Password",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("********") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(8.dp),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = "Toggle password visibility"
                                )
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = LightBlue,
                            unfocusedContainerColor = LightBlue,
                            focusedIndicatorColor = PrimaryBlue,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { navController.navigate("home/Administrador") },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            text = "Iniciar Sesión Admin",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }

                    Button(
                        onClick = { navController.navigate("home/Doctor") },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            text = "Iniciar Sesión Doctor",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }

                    Button(
                        onClick = { navController.navigate("home/Paciente") },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            text = "Iniciar Sesión Paciente",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "o registrate con",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "ingresar como invitado",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue,
                        modifier = Modifier.clickable { navController.navigate("home/Invitado") }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "¿No tienes una cuenta? ",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Regístrate",
                        fontSize = 14.sp,
                        color = PrimaryBlue,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { navController.navigate("register") }
                    )
                }
            }
        }
    )
}
