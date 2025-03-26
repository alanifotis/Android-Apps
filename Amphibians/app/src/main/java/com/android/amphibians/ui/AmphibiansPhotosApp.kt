package com.android.amphibians.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.*
import com.android.amphibians.R
import com.android.amphibians.ui.screen.AmphibiansViewModel
import com.android.amphibians.ui.screen.HomeScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.amphibians.AmphibiansPhotosApplication


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansPhotosApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineMedium
                )
            })
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val amphibiansViewModel: AmphibiansViewModel =
                viewModel(factory = AmphibiansViewModel.Factory)
            HomeScreen(
                amphibiansUiState = amphibiansViewModel.amphibiansUiState,
                retryAction = { /*TODO*/ },
                modifier = Modifier.fillMaxSize(),
                contentPadding = it
            )
        }
    }
}