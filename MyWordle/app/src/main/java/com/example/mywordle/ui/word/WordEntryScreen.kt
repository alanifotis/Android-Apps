package com.example.mywordle.ui.word

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mywordle.R
import com.example.mywordle.WordTopAppBar
import com.example.mywordle.ui.AppViewModelProvider
import com.example.mywordle.ui.navigation.NavigationDestination
import com.example.mywordle.ui.theme.WordTheme
import kotlinx.coroutines.launch

object WordEntryDestination : NavigationDestination {
    override val route = "word_entry"
    override val titleRes = R.string.word_entry
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: WordEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            WordTopAppBar(
                title = stringResource(WordEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        WordEntryBody(
            wordUiState = viewModel.wordUiState,
            onWordValueChange = viewModel::updateUiState,
            onSaveClick = {
                /** Note: If the user rotates the screen very fast, the operation may get cancelled
                 * and the item may not be saved in the Database. This is because when config
                 * change occurs, the Activity will be recreated and the rememberCoroutineScope will
                 * be cancelled - since the scope is bound to composition.
                */
                coroutineScope.launch {
                    viewModel.saveWord()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun WordEntryBody(
    wordUiState: WordUiState,
    onWordValueChange: (WordDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        WordInputForm(
            wordDetails = wordUiState.wordDetails,
            onValueChange = onWordValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = wordUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_action))
        }
    }
}

@Composable
fun WordInputForm(
    wordDetails: WordDetails,
    modifier: Modifier = Modifier,
    onValueChange: (WordDetails) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = wordDetails.wordToBeInserted,
            onValueChange = { onValueChange(wordDetails.copy(wordToBeInserted = it)) },
            label = { Text(stringResource(R.string.word_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled) {
            Text(
                text = stringResource(R.string.required_fields),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WordEntryScreenPreview() {
    WordTheme {
        WordEntryBody(wordUiState = WordUiState(
            WordDetails(
                id = 99, wordToBeInserted = "example"
            )
        ), onWordValueChange = {}, onSaveClick = {})
    }
}
