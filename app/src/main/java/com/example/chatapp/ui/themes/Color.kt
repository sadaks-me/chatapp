package com.example.chatapp.ui.themes

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val primaryColor = Color(0xFFFFFFFF)
val activeColor = Color(0xFFF5406C)
val tertiaryColor = Color(0xFFFFC371)
val inactiveColor = Color(0xFFf0f5fa)
val inactiveTextColor = Color(0xFF3c5667)
val dateTextColor = Color(0xFFbcc3c8)
val defaultGradient = Brush.verticalGradient(
    colors = listOf(
        activeColor,
        activeColor,
        tertiaryColor
    )
)