package com.example.demotipcalculatorkmp

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.TipCalculatorScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Calculate Tip - KMP Desktop"
    ) {
        MaterialTheme {
            TipCalculatorScreen()
        }
    }
}