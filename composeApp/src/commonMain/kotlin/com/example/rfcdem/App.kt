package com.example.rfcdem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import rfcdem.composeapp.generated.resources.Res
import rfcdem.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun RfcApp() {
    var nombre by remember { mutableStateOf("") }
    var paterno by remember { mutableStateOf("") }
    var materno by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    val rfc = remember(nombre, paterno, materno, fecha) {
        RfcCalculator.calcular(nombre, paterno, materno, fecha)
    }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(24.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Calculadora de RFC (SAT)", style = MaterialTheme.typography.headlineMedium)

                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text(
                        text = rfc,
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(20.dp)
                    )
                }

                OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre(s)") })
                OutlinedTextField(value = paterno, onValueChange = { paterno = it }, label = { Text("Apellido Paterno") })
                OutlinedTextField(value = materno, onValueChange = { materno = it }, label = { Text("Apellido Materno (Opcional)") })
                OutlinedTextField(
                    value = fecha,
                    onValueChange = { if (it.length <= 8) fecha = it },
                    label = { Text("Fecha Nacimiento (YYYYMMDD)") },
                    placeholder = { Text("Ej. 19950524") }
                )
            }
        }
    }
}

object RfcCalculator
{
    private val PALABRAS_INCONVENIENTES = setOf("BUEI",
        "KAKA",
        "LOCA",
        "KOJO",
        "PEDO",
        "PUTO",
        "QULO")

    fun calcular
    (
        nombre: String,
        paterno: String,
        materno: String,
        fechaNacimiento: String
    ): String
    {
        if (paterno.length < 2 || nombre.isEmpty()) return "____-______"

        val letra1 = paterno.first().uppercase()
        val vocal1 = paterno.drop(1).firstOrNull { it.uppercaseChar() in "AEIOU" }?.uppercase() ?: "X"

        val letra3 = if (materno.isNotEmpty()) materno.first().uppercase() else "X"

        val nombresSep = nombre.trim().split(" ")
        val nombreUsar = if (nombresSep.size > 1 && (nombresSep[0].uppercase() == "JOSE" || nombresSep[0].uppercase() == "MARIA")) {
            nombresSep[1]
        } else {
            nombresSep[0]
        }
        val letra4 = nombreUsar.first().uppercase()

        var cuatroLetras = "$letra1$vocal1$letra3$letra4"

        if (PALABRAS_INCONVENIENTES.contains(cuatroLetras)) {
            cuatroLetras = cuatroLetras.substring(0, 3) + "X"
        }

        val fechaLimpia = fechaNacimiento.replace("-", "")
        val cadenaFecha = if (fechaLimpia.length >= 8) {
            fechaLimpia.substring(2, 8)
        } else "000000"

        return cuatroLetras + cadenaFecha
    }
}