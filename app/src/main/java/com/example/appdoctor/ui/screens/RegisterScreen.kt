package com.example.appdoctor.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appdoctor.ui.components.Header
import com.example.appdoctor.ui.theme.PrimaryBlue
import com.example.appdoctor.ui.theme.LightBlue

@Composable
fun RegisterScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") }
    var departamento by remember { mutableStateOf("") }
    var provincia by remember { mutableStateOf("") }
    var distrito by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("adulto") }
    var passwordVisible by remember { mutableStateOf(false) }

    val tipos = listOf("niño", "joven", "adulto", "adulto mayor")

    Scaffold(
        topBar = {
            Header(
                title = "Nueva Cuenta",
                onBackClick = { navController.popBackStack() }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InputField("Nombre", nombre) { nombre = it }
                    InputField("Apellido", apellido) { apellido = it }
                    InputField("DNI", dni) { dni = it }
                    DropdownField(
                        label = "Género",
                        selectedOption = genero,
                        options = listOf("Femenino", "Masculino"),
                        onOptionSelected = { genero = it }
                    )
                    InputField("Dirección", direccion) { direccion = it }
                    InputField("Teléfono", telefono) { telefono = it }
                    InputField("Email", email) { email = it }

                    // Campo para Fecha de Nacimiento
                    InputField("Fecha de nacimiento", fechaNacimiento) { fechaNacimiento = it }

                    // Campo para Edad
                    InputField("Edad", edad) { edad = it }

                    // Dropdown para Tipo
                    DropdownField(
                        label = "Tipo",
                        selectedOption = tipo,
                        options = tipos,
                        onOptionSelected = { tipo = it }
                    )

                    PasswordField(password, passwordVisible,
                        onPasswordChange = { password = it },
                        onVisibilityChange = { passwordVisible = it }
                    )

                    InputField("Usuario", usuario) { usuario = it }
                    InputField("Departamento", departamento) { departamento = it }
                    InputField("Provincia", provincia) { provincia = it }
                    InputField("Distrito", distrito) { distrito = it }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate("login") },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            text = "Registrar Usuario",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }


                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "or sign up with",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = "¿Ya tienes una cuenta? Iniciar sesión",
                        fontSize = 14.sp,
                        color = PrimaryBlue,
                        modifier = Modifier.clickable { navController.navigate("login") }
                    )
                }
            }
        }
    )
}

@Composable
fun InputField(label: String, value: String, onValueChange: (String) -> Unit) {
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
fun DropdownField(
    label: String,
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )
        Box {
            TextField(
                value = selectedOption,
                onValueChange = { },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Abrir menú desplegable"
                        )
                    }
                },
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
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PasswordField(
    password: String,
    passwordVisible: Boolean,
    onPasswordChange: (String) -> Unit,
    onVisibilityChange: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Password",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = { Text("********") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { onVisibilityChange(!passwordVisible) }) {
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
    }
}
