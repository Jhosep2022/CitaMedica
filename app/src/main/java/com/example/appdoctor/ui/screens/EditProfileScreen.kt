@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.appdoctor.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdoctor.R
import com.example.appdoctor.data.model.Persona
import com.example.appdoctor.ui.components.Header
import com.example.appdoctor.ui.theme.PrimaryBlue
import com.example.appdoctor.ui.viewmodel.PersonaViewModel

@Composable
fun EditProfileScreen(
    viewModel: PersonaViewModel,
    onBackClick: () -> Unit = {},
    onUpdateClick: (Persona) -> Unit = {}
) {
    // Obtener datos del usuario autenticado desde el ViewModel
    val usuario = viewModel.usuarioAutenticado

    // Variables de estado para los campos editables
    var name by remember { mutableStateOf(usuario?.nombres ?: "") }
    var lastName by remember { mutableStateOf(usuario?.apellidos ?: "") }
    var dni by remember { mutableStateOf(usuario?.dni ?: "") }
    var gender by remember { mutableStateOf(usuario?.genero ?: "") }
    var address by remember { mutableStateOf(usuario?.direccion ?: "") }
    var username by remember { mutableStateOf(usuario?.usuario ?: "") }
    var department by remember { mutableStateOf(usuario?.idDepartamento?.toString() ?: "") }
    var province by remember { mutableStateOf(usuario?.idProvincia?.toString() ?: "") }
    var district by remember { mutableStateOf(usuario?.idDistrito?.toString() ?: "") }

    // Selección dinámica de la imagen de perfil basada en el género
    val profileImage = when (gender.lowercase()) {
        "m", "masculino" -> R.drawable.user1 // Imagen para género masculino
        "f", "femenino" -> R.drawable.user2 // Imagen para género femenino
        else -> R.drawable.user1 // Imagen predeterminada
    }

    Scaffold(
        topBar = {
            // Usamos el componente Header
            Header(title = "Editar Perfil", onBackClick = onBackClick)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Imagen de perfil con botón de editar
            Box(contentAlignment = Alignment.BottomEnd) {
                Image(
                    painter = painterResource(id = profileImage), // Usamos la imagen seleccionada dinámicamente
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Profile Picture",
                    tint = PrimaryBlue,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp)
                )
            }

            // Editable fields
            EditableField(label = "Nombre", value = name, onValueChange = { name = it })
            EditableField(label = "Apellido", value = lastName, onValueChange = { lastName = it })
            EditableField(label = "DNI", value = dni, onValueChange = { dni = it })
            EditableField(label = "Género", value = gender, onValueChange = { gender = it })
            EditableField(label = "Dirección", value = address, onValueChange = { address = it })
            EditableField(label = "Usuario", value = username, onValueChange = { username = it })
            EditableField(label = "Departamento", value = department, onValueChange = { department = it })
            EditableField(label = "Provincia", value = province, onValueChange = { province = it })
            EditableField(label = "Distrito", value = district, onValueChange = { district = it })

            Spacer(modifier = Modifier.height(16.dp))

            // Update button
            Button(
                onClick = {
                    val updatedPersona = usuario?.copy(
                        nombres = name,
                        apellidos = lastName,
                        dni = dni,
                        genero = gender,
                        direccion = address,
                        usuario = username,
                        idDepartamento = department.toIntOrNull() ?: usuario.idDepartamento,
                        idProvincia = province.toIntOrNull() ?: usuario.idProvincia,
                        idDistrito = district.toIntOrNull() ?: usuario.idDistrito
                    )
                    if (updatedPersona != null) {
                        onUpdateClick(updatedPersona)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Actualizar Perfil",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun EditableField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = PrimaryBlue
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEFEFEF), RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEFEFEF),
                focusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}
