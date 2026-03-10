package ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TipUiState(
    val billAmount: String = "",
    val tipPercentage: String = "15",
    val roundUp: Boolean = false,
    val tipResult: String = "0.00"
)

class TipCalculatorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TipUiState())
    val uiState: StateFlow<TipUiState> = _uiState.asStateFlow()

    fun onBillAmountChange(newValue: String) {
        _uiState.update { it.copy(billAmount = newValue) }
        recalculateTip()
    }

    fun onTipPercentageChange(newValue: String) {
        _uiState.update { it.copy(tipPercentage = newValue) }
        recalculateTip()
    }

    fun onRoundUpChange(newValue: Boolean) {
        _uiState.update { it.copy(roundUp = newValue) }
        recalculateTip()
    }

    private fun recalculateTip() {
        val amount = _uiState.value.billAmount.toDoubleOrNull() ?: 0.0
        val percent = _uiState.value.tipPercentage.toDoubleOrNull() ?: 0.0

        var tip = amount*(percent/100)

        if (_uiState.value.roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        _uiState.update { it.copy(tipResult = tip.toString()) }
    }

    override fun onCleared() {
        super.onCleared()
        println(">>> LOG: ViewModel limpiado correctamente (onCleared)")
    }
}