package com.example.appdoctor.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appdoctor.ui.components.Header
import com.example.appdoctor.ui.theme.PrimaryBlue
import com.example.appdoctor.ui.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDoctorScreen(navController: NavController) {
    // Variables para almacenar los valores de los inputs
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var codigoEmpleado by remember { mutableStateOf("") }
    var cargo by remember { mutableStateOf("") }
    var origen by remember { mutableStateOf("") }
    var especialidad by remember { mutableStateOf("") }
    var consultorio by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Header(
                title = "Nueva Cuenta",
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            LightBlue.copy(alpha = 0.5f),
                            Color.White
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Inputs Básicos
                InputField("Nombre", nombre) { nombre = it }
                InputField("Apellido", apellido) { apellido = it }
                InputField("DNI", dni, KeyboardOptions(keyboardType = KeyboardType.Number)) { dni = it }
                DropdownField(
                    label = "Género",
                    selectedOption = genero,
                    options = listOf("Femenino", "Masculino"),
                    onOptionSelected = { genero = it }
                )
                InputField("Dirección", direccion) { direccion = it }
                InputField("Teléfono", telefono, KeyboardOptions(keyboardType = KeyboardType.Phone)) { telefono = it }
                InputField("Email", email, KeyboardOptions(keyboardType = KeyboardType.Email)) { email = it }

                // Inputs Adicionales
                InputField("Código Empleado", codigoEmpleado) { codigoEmpleado = it }
                InputField("Cargo", cargo) { cargo = it }
                InputField("Origen", origen) { origen = it }
                InputField("Especialidad", especialidad) { especialidad = it }
                InputField("Consultorio", consultorio) { consultorio = it }

                // Campo de Contraseña
                PasswordField(
                    label = "Contraseña",
                    password = password,
                    passwordVisible = passwordVisible,
                    onPasswordChange = { password = it },
                    onVisibilityChange = { passwordVisible = it }
                )

                // Botón de Registro
                Button(
                    onClick = { /* Acción para registrar al doctor */ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = "Registrar Doctor",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun InputField(
    label: String,
    value: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(label.lowercase()) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightBlue,
                unfocusedContainerColor = LightBlue,
                focusedIndicatorColor = PrimaryBlue,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun PasswordField(
    label: String,
    password: String,
    passwordVisible: Boolean,
    onPasswordChange: (String) -> Unit,
    onVisibilityChange: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = PrimaryBlue,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { onVisibilityChange(!passwordVisible) }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password Visibility"
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
    }
}
