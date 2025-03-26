package com.example.mywordle.ui.online.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.mywordle.R
import com.example.mywordle.WordTopAppBar
import com.example.mywordle.ui.navigation.NavigationDestination
import com.example.mywordle.ui.online.ui.MultiPlayerScreenDestination
import com.example.mywordle.ui.word.WordDetails
import androidx.compose.runtime.collectAsState


object OnlineSinglePlayerScreenDestination : NavigationDestination {
    override val route = "online_single_player_screen"
    override val titleRes = R.string.online_single_player
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnlineSinglePlayerScreen(
    onlineSinglePlayerView: OnlineSinglePlayerView,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            WordTopAppBar(
                title = stringResource(MultiPlayerScreenDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        val wordDetails by onlineSinglePlayerView.onlineSinglePlayerUiState.collectAsState()

        PlayScreen(
            wordDetails,
            modifier = modifier.padding(innerPadding)
        )
    }
}


@Composable
fun PlayScreen(wordDetails: WordDetails, modifier: Modifier = Modifier) {
    val hiddenWord = wordDetails.wordToBeInserted
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = stringResource(R.string.app_name),
            alignment = Alignment.Center,
        )
        Text(
            text = hiddenWord,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}
