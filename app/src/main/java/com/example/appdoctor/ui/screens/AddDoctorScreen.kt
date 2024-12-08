package com.example.appdoctor.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appdoctor.data.model.Doctor
import com.example.appdoctor.data.model.Ubigeo
import com.example.appdoctor.ui.components.Header
import com.example.appdoctor.ui.theme.PrimaryBlue
import com.example.appdoctor.ui.theme.LightBlue
import com.example.appdoctor.ui.viewmodel.PersonaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDoctorScreen(navController: NavController, viewModel: PersonaViewModel) {
    // Variables para los datos del formulario
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

    var ubigeos by remember { mutableStateOf(emptyList<Ubigeo>()) }
    var departamentos by remember { mutableStateOf(emptyList<Ubigeo>()) }
    var provincias by remember { mutableStateOf(emptyList<Ubigeo>()) }
    var distritos by remember { mutableStateOf(emptyList<Ubigeo>()) }

    var selectedDepartamento by remember { mutableStateOf<Ubigeo?>(null) }
    var selectedProvincia by remember { mutableStateOf<Ubigeo?>(null) }
    var selectedDistrito by remember { mutableStateOf<Ubigeo?>(null) }

    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.fetchUbigeos { result ->
            ubigeos = result
            departamentos = ubigeos.distinctBy { it.idDepartamento }
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            Header(title = "Registrar Doctor", onBackClick = { navController.popBackStack() })
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(LightBlue.copy(alpha = 0.5f), Color.White)
                    )
                )
                .padding(innerPadding)
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                    InputField("Código Empleado", codigoEmpleado) { codigoEmpleado = it }
                    InputField("Cargo", cargo) { cargo = it }
                    InputField("Origen", origen) { origen = it }
                    InputField("Especialidad", especialidad) { especialidad = it }
                    InputField("Consultorio", consultorio) { consultorio = it }

                    PasswordField(
                        label = "Contraseña",
                        password = password,
                        passwordVisible = passwordVisible,
                        onPasswordChange = { password = it },
                        onVisibilityChange = { passwordVisible = it }
                    )

                    // Manejo de Ubigeo
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

                    Button(
                        onClick = {
                            val doctor = Doctor(
                                idEspecialidad = especialidad.toIntOrNull() ?: 0,
                                idConsultorio = consultorio.toIntOrNull() ?: 0,
                                codCmp = codigoEmpleado,
                                cargo = cargo,
                                origen = origen,
                                id = null,
                                nombres = nombre,
                                apellidos = apellido,
                                dni = dni,
                                genero = genero,
                                direccion = direccion,
                                telefono = telefono,
                                email = email,
                                usuario = email.split("@")[0],
                                clave = password,
                                rolId = 3,
                                idDepartamento = selectedDepartamento?.idDepartamento?.toInt() ?: 0,
                                idProvincia = selectedProvincia?.idProvincia?.toInt() ?: 0,
                                idDistrito = selectedDistrito?.idDistrito?.toInt() ?: 0
                            )
                            viewModel.registrarDoctor(doctor) { success ->
                                if (success) {
                                    navController.popBackStack()
                                } else {
                                    println("Error al registrar el doctor")
                                }
                            }
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Registrar Doctor", fontSize = 16.sp, color = Color.White)
                    }
                }
            }
        }
    }
}
@Composable
fun InputField(
    label: String,
    value: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default, // Parámetro opcional
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontSize = 16.sp, color = Color.Black)
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(label.lowercase()) },
            keyboardOptions = keyboardOptions, // Asigna el parámetro aquí
            modifier = Modifier.fillMaxWidth(),
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
        Text(text = label, fontSize = 16.sp, color = PrimaryBlue)
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { onVisibilityChange(!passwordVisible) }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
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


