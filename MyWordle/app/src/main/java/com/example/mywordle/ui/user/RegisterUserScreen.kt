package com.example.mywordle.ui.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.mywordle.R
import com.example.mywordle.WordTopAppBar
import com.example.mywordle.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object RegisterUserScreenDestination : NavigationDestination {
    override val route = "register_user"
    override val titleRes = R.string.register
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUserScreen(
    userViewModel: RegisterUserViewModel,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            WordTopAppBar(
                title = stringResource(RegisterUserScreenDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding: PaddingValues ->
        UserRegister(
            userUiState = userViewModel.userUiState ,
            onUserDataInput = userViewModel::updateUiState,
            onSaveClick = {
                /** Note: If the user rotates the screen very fast, the operation may get cancelled
                 * and the item may not be saved in the Database. This is because when config
                 * change occurs, the Activity will be recreated and the rememberCoroutineScope will
                 * be cancelled - since the scope is bound to composition.
                 */
                /** Note: If the user rotates the screen very fast, the operation may get cancelled
                 * and the item may not be saved in the Database. This is because when config
                 * change occurs, the Activity will be recreated and the rememberCoroutineScope will
                 * be cancelled - since the scope is bound to composition.
                 */
                coroutineScope.launch {
                    userViewModel.saveUser()
                    navigateBack()
                }
            },
            modifier = modifier
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
fun UserRegister(
    userUiState: UserUiState,
    onUserDataInput: (UserDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        UserInputForm(
            userDetails = userUiState.userDetails,
            onValueChange = onUserDataInput,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = userUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.register))
        }
    }
}

@Composable
fun UserInputForm(
    userDetails: UserDetails,
    modifier: Modifier = Modifier,
    onValueChange: (UserDetails) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = userDetails.userNameToBeInserted,
            onValueChange = { onValueChange(userDetails.copy(userNameToBeInserted = it)) },
            label = { Text(stringResource(R.string.user_name)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = userDetails.userEmailToBeInserted,
            onValueChange = { onValueChange(userDetails.copy(userEmailToBeInserted = it)) },
            label = { Text(stringResource(R.string.email)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = userDetails.userPasswordToBeInserted,
            onValueChange = { onValueChange(userDetails.copy(userPasswordToBeInserted = it)) },
            label = { Text(stringResource(R.string.password)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
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

