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
import com.example.appdoctor.ui.components.Header
import com.example.appdoctor.ui.theme.PrimaryBlue

@Composable
fun EditProfileScreen(onBackClick: () -> Unit = {}, onUpdateClick: () -> Unit = {}) {
    var name by remember { mutableStateOf("John Doe") }
    var lastName by remember { mutableStateOf("Casd") }
    var dni by remember { mutableStateOf("16554989") }
    var gender by remember { mutableStateOf("masculino") }
    var address by remember { mutableStateOf("av. asdvas calle. asd") }
    var username by remember { mutableStateOf("aadsadv") }
    var department by remember { mutableStateOf("puno") }
    var province by remember { mutableStateOf("ludfa") }
    var district by remember { mutableStateOf("distrito 1") }

    Scaffold(
        topBar = {
            // Usamos el componente Header
            Header(title = "Perfil", onBackClick = onBackClick)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()), // Habilita el scroll
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Imagen de perfil con botón de editar
            Box(contentAlignment = Alignment.BottomEnd) {
                Image(
                    painter = painterResource(id = R.drawable.hombre),
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

            // Role text
            Text(
                text = "ADMIN",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue
            )

            // Editable fields
            EditableField(label = "Nombre", value = name, onValueChange = { name = it })
            EditableField(label = "Apellido", value = lastName, onValueChange = { lastName = it })
            EditableField(label = "Dni", value = dni, onValueChange = { dni = it })
            EditableField(label = "Genero", value = gender, onValueChange = { gender = it })
            EditableField(label = "Direccion", value = address, onValueChange = { address = it })
            EditableField(label = "Usuario", value = username, onValueChange = { username = it })
            EditableField(label = "Departamento", value = department, onValueChange = { department = it })
            EditableField(label = "Provincia", value = province, onValueChange = { province = it })
            EditableField(label = "Distrito", value = district, onValueChange = { district = it })

            Spacer(modifier = Modifier.height(16.dp))

            // Update button
            Button(
                onClick = onUpdateClick,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Update Profile",
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
