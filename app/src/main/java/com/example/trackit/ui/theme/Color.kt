package com.example.trackit.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)

//priority colors
val LowPriorityColor = Color(0xFF00C980)
val MediumPriorityColor = Color(0xFFFFc114)
val HighPriorityColor = Color(0xFFFF4646)
val NonePriorityColor = MediumGray

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) Color.Black else Color.White

val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else Color.Black

val Colors.bottomAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else Color.Black

val Colors.bottomAppBarContentColor: Color
    @Composable
    get() = if (isLight) MediumGray else LightGray

val Colors.fabBackgroundColor: Color
    @Composable
    get() = if (isLight) Teal200 else Purple700