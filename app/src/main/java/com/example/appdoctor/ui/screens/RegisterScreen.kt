
package com.example.appdoctor.ui.screens

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
import com.example.appdoctor.data.model.Paciente
import com.example.appdoctor.data.model.Ubigeo
import com.example.appdoctor.ui.components.Header
import com.example.appdoctor.ui.theme.PrimaryBlue
import com.example.appdoctor.ui.theme.LightBlue
import com.example.appdoctor.ui.viewmodel.PersonaViewModel

@Composable
fun RegisterScreen(navController: NavController, viewModel: PersonaViewModel) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("Regular") }

    var ubigeos by remember { mutableStateOf(emptyList<Ubigeo>()) }
    var departamentos by remember { mutableStateOf(emptyList<Ubigeo>()) }
    var provincias by remember { mutableStateOf(emptyList<Ubigeo>()) }
    var distritos by remember { mutableStateOf(emptyList<Ubigeo>()) }

    var selectedDepartamento by remember { mutableStateOf<Ubigeo?>(null) }
    var selectedProvincia by remember { mutableStateOf<Ubigeo?>(null) }
    var selectedDistrito by remember { mutableStateOf<Ubigeo?>(null) }

    var isLoading by remember { mutableStateOf(true) }
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.fetchUbigeos { result ->
            ubigeos = result
            departamentos = ubigeos.distinctBy { it.idDepartamento }
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            Header(title = "Registrar Paciente", onBackClick = { navController.popBackStack() })
        },
        content = { innerPadding ->
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    InputField("Nombre", nombre) { nombre = it }
                    InputField("Apellido", apellido) { apellido = it }
                    InputField("DNI", dni) { dni = it }
                    DropdownField("Género", genero, listOf("Masculino", "Femenino")) { genero = it }
                    InputField("Dirección", direccion) { direccion = it }
                    InputField("Teléfono", telefono) { telefono = it }
                    InputField("Email", email) { email = it }
                    InputField("Usuario", usuario) { usuario = it }
                    PasswordField(
                        password = password,
                        passwordVisible = passwordVisible,
                        onPasswordChange = { password = it },
                        onVisibilityChange = { passwordVisible = it }
                    )
                    InputField("Fecha de Nacimiento (YYYY-MM-DD)", fechaNacimiento) { fechaNacimiento = it }
                    InputField("Edad", edad) { edad = it }

                    DropdownField(
                        label = "Departamento",
                        selectedOption = selectedDepartamento?.descDepartamento ?: "",
                        options = departamentos.map { it.descDepartamento },
                        onOptionSelected = { selected ->
                            selectedDepartamento = departamentos.find { it.descDepartamento == selected }
                            provincias = ubigeos.filter { it.idDepartamento == selectedDepartamento?.idDepartamento }
                            selectedProvincia = null
                            selectedDistrito = null
                        }
                    )
                    DropdownField(
                        label = "Provincia",
                        selectedOption = selectedProvincia?.descProvincia ?: "",
                        options = provincias.map { it.descProvincia },
                        onOptionSelected = { selected ->
                            selectedProvincia = provincias.find { it.descProvincia == selected }
                            distritos = ubigeos.filter { it.idProvincia == selectedProvincia?.idProvincia }
                            selectedDistrito = null
                        }
                    )
                    DropdownField(
                        label = "Distrito",
                        selectedOption = selectedDistrito?.descDistrito ?: "",
                        options = distritos.map { it.descDistrito },
                        onOptionSelected = { selected ->
                            selectedDistrito = distritos.find { it.descDistrito == selected }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (fechaNacimiento.isNotEmpty() && fechaNacimiento.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))) {
                                val paciente = Paciente(
                                    nombres = nombre,
                                    apellidos = apellido,
                                    dni = dni,
                                    genero = genero,
                                    direccion = direccion,
                                    telefono = telefono,
                                    email = email,
                                    usuario = usuario,
                                    clave = password,
                                    rolId = 2,
                                    idDepartamento = (selectedDepartamento?.idDepartamento ?: 0).toInt(),
                                    idProvincia = (selectedProvincia?.idProvincia ?: 0).toInt(),
                                    idDistrito = (selectedDistrito?.idDistrito ?: 0).toInt(),
                                    fechaNacimiento = fechaNacimiento, // Formato YYYY-MM-DD
                                    edad = edad.toIntOrNull() ?: 0,
                                    tipo = tipo
                                )

                                // Log detallado de los datos
                                println("Datos enviados al backend (Paciente): ${paciente}")

                                viewModel.registrarPaciente(paciente) { success ->
                                    if (success) {
                                        println("Paciente registrado correctamente.")
                                        navController.navigate("login")
                                    } else {
                                        println("Error al registrar paciente.")
                                    }
                                }
                            } else {
                                println("Error: Fecha de nacimiento no válida o vacía. Asegúrate de usar el formato YYYY-MM-DD.")
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Registrar Paciente", color = Color.White)
                    }

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
