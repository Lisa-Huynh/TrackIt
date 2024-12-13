package com.example.trackit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    surface = WalletBrown,
)

private val LightColorScheme = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    surface = WalletBrown,
)

@Composable
fun AppTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkMode) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}