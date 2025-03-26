package com.android.flightsearch.ui.home

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier



@Composable
fun Greeting(name: String,
             onSearchInput: (String) -> Unit,
             modifier: Modifier = Modifier
) {
    Text(text = name)
    TextField(value = "Search Flights",
        onValueChange = onSearchInput
    )
}