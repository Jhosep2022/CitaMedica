package com.example.appdoctor.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = Background,
    secondary = LightBlue,
    onSecondary = TextBlack,
    background = Background,
    onBackground = TextBlack,
    surface = Background,
    onSurface = TextBlack,
    error = Color(0xFFB00020),
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkBlue,
    onPrimary = LightGray,
    secondary = LightBlue,
    onSecondary = LightGray,
    background = DarkGray,
    onBackground = LightGray,
    surface = DarkGray,
    onSurface = LightGray,
    error = Color(0xFFCF6679),
    onError = Color.Black
)

@Composable
fun AppDoctorTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

