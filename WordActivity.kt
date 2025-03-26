package com.example.mywordle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mywordle.ui.theme.WordTheme
import com.example.mywordle.R

class WordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainView(modifier: Modifier = Modifier) {
    var guessInput by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(10.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo Image",
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = guessInput,
            onValueChange = { guessInput = it },
            singleLine = true,
            label = { Text(stringResource(R.string.take_a_guess)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.insert_text),
                    contentDescription = "Insert Guess"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Process the text here
                    println("Entered text: $guessInput")
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}

@Preview
@Composable
fun SimpleComposablePreview() {
    MainView()
}