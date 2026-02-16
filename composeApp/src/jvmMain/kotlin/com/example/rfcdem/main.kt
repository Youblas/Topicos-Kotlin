package com.example.rfcdem

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.unit.dp

fun main() = application {
    val state = rememberWindowState(width = 500.dp, height = 700.dp)
    Window(
        onCloseRequest = ::exitApplication,
        title = "Generador de RFC - KMP Desktop",
        state = state
    ) {
        RfcApp()
    }
}

//hola