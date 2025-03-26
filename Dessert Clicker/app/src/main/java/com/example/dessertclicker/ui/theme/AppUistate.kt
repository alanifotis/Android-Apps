package com.example.dessertclicker.ui.theme

import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert

data class AppUiState (
    val desserts: List<Dessert> = Datasource.dessertList,
    var revenue: Int = 0,
    var dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
)