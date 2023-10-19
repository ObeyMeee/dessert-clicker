package ua.com.andromeda.dessertclicker.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ua.com.andromeda.dessertclicker.data.Datasource
import ua.com.andromeda.dessertclicker.data.DessertUiState
import ua.com.andromeda.dessertclicker.model.Dessert

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    private val desserts = Datasource.dessertList

    fun clickDessert() {
        val dessertToShow = determineDessertToShow()
        _uiState.update { currentState ->
            currentState.copy(
                revenue = currentState.revenue + currentState.currentDessertPrice,
                amountDessertsSold = currentState.amountDessertsSold + 1,
                currentDessertImageId = dessertToShow.imageId,
                currentDessertPrice = dessertToShow.price
            )
        }
    }

    /**
     * Determine which dessert to show.
     */
    private fun determineDessertToShow(): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (_uiState.value.amountDessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
}