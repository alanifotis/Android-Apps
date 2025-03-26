package com.android.flightsearch.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeScreenViewModel : ViewModel() {
    private val uiState = mutableStateOf(List<Airports>)

    fun onTextInput(searchInput: String): Unit {
        uiState = AirpotsDao.getAllAirports()
    }
}