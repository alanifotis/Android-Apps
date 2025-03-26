package com.example.mywordle.ui.online.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mywordle.R
import com.example.mywordle.WordTopAppBar
import com.example.mywordle.ui.navigation.NavigationDestination
import com.example.mywordle.ui.home.WordList
import com.example.mywordle.model.Word

object MultiPlayerScreenDestination : NavigationDestination {
    override val route = "multiplayer_screen"
    override val titleRes = R.string.multiplayer_screen_title
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiPlayerScreen(
    multiplayerUiState: MultiplayerUiState,
    retryAction: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,

) {
    Scaffold(
        topBar = {
            WordTopAppBar(
                title = stringResource(MultiPlayerScreenDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding: PaddingValues ->
        when (multiplayerUiState) {
            is MultiplayerUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is MultiplayerUiState.Success -> WordList(
                multiplayerUiState.words.map { onlineWord ->
                    Word(
                        id = onlineWord.id,
                        hiddenWord = onlineWord.hiddenWord
                    )
                },
                contentPadding = innerPadding,
                onWordClick = {

                },
                modifier = modifier.fillMaxWidth()
            )

            is MultiplayerUiState.Error -> ErrorScreen(
                retryAction,
                modifier = modifier.fillMaxSize()
            )
        }

    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

