package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.HeroesRepository
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperheroApp()
                }
            }
        }
    }
}

@Composable
fun SuperheroApp() {
    Scaffold(
        topBar = {
            SuperheroesopAppBar()
        }
    ) { it ->
        LazyColumn(
            contentPadding = it)
        {
                items(HeroesRepository.heroes) {
                    CardElement(
                        hero = it,
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .fillMaxSize()
                    )
                }
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperheroesTheme (darkTheme = true) {
        SuperheroApp()
    }
}