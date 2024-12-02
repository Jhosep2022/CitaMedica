package com.example.appdoctor.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.appdoctor.ui.theme.PrimaryBlue

@Composable
fun HomeBottomBar(
    selectedTab: String,
    role: String,
    onHomeClick: () -> Unit,
    onChatClick: () -> Unit,
    onProfileClick: () -> Unit,
    onScheduleClick: () -> Unit,
    onAddUserClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    color = PrimaryBlue,
                    shape = RoundedCornerShape(50.dp)
                )
                .padding(horizontal = 1.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                icon = Icons.Default.Home,
                contentDescription = "Inicio",
                isSelected = selectedTab == "Home",
                onClick = onHomeClick
            )

            // Mostrar u ocultar el ítem de Chat según el rol
            if (role != "Administrador" && role != "Invitado" && role != "Paciente") {
                BottomBarItem(
                    icon = Icons.Default.Chat,
                    contentDescription = "Chat",
                    isSelected = selectedTab == "Chat",
                    onClick = {
                        onChatClick()
                    }
                )
            }

            BottomBarItem(
                icon = Icons.Default.Person,
                contentDescription = "Perfil",
                isSelected = selectedTab == "Profile",
                onClick = onProfileClick
            )

            BottomBarItem(
                icon = Icons.Default.CalendarMonth,
                contentDescription = "Calendario",
                isSelected = selectedTab == "Schedule",
                onClick = onScheduleClick
            )

            // Mostrar el ítem para agregar usuario solo para Administrador
            if (role == "Administrador") {
                BottomBarItem(
                    icon = Icons.Default.PersonAdd,
                    contentDescription = "Agregar Usuario",
                    isSelected = selectedTab == "addDoctor",
                    onClick = {
                        onAddUserClick()
                    }
                )

            }
        }
    }
}

@Composable
fun BottomBarItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(50.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = if (isSelected) Color(0xFF00278C) else Color.White,
            modifier = Modifier.size(28.dp)
        )
    }
}
