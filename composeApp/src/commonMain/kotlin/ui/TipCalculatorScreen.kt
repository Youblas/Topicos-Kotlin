package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ViewModel.TipCalculatorViewModel

@Composable
fun TipCalculatorScreen(
    tipViewModel: TipCalculatorViewModel = viewModel { TipCalculatorViewModel() }
) {
    val uiState by tipViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Calculadora de Propinas", style = MaterialTheme.typography.h5)

        TextField(
            value = uiState.billAmount,
            onValueChange = { tipViewModel.onBillAmountChange(it) },
            label = { Text("Monto de la cuenta") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = uiState.tipPercentage,
            onValueChange = { tipViewModel.onTipPercentageChange(it) },
            label = { Text("% de Propina") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("¿Redondear propina?")
            Spacer(Modifier.weight(1f))
            Switch(
                checked = uiState.roundUp,
                onCheckedChange = { tipViewModel.onRoundUpChange(it) }
            )
        }

        Divider()

        Text(
            text = "Total Propina: $${uiState.tipResult}",
            style = MaterialTheme.typography.h4
        )
    }
}