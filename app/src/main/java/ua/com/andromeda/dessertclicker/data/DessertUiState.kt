package ua.com.andromeda.dessertclicker.data

import androidx.annotation.DrawableRes
import ua.com.andromeda.dessertclicker.data.Datasource.dessertList

data class DessertUiState(
    val amountDessertsSold: Int = 0,
    val revenue: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = dessertList[currentDessertIndex].price,
    @DrawableRes val currentDessertImageId: Int = dessertList[currentDessertIndex].imageId
)