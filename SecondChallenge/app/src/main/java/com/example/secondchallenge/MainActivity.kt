package com.example.secondchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.secondchallenge.ui.theme.SecondChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecondChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TaskCompleted(Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun TextTaskCompleted(modifier: Modifier = Modifier) {
    val tasks: String = stringResource(R.string.tasks_completed)
    val congratulations: String = stringResource(R.string.nice_work)

    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = tasks,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(top = 24.dp, bottom = 8.dp)

        )
        Text(
            text = congratulations,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)

        )
    }
}

@Composable
fun TaskCompleted(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.ic_task_completed)

    Box(modifier
        ) {
        Column (
            modifier = Modifier.align(Alignment.Center)) {
            Image(
                painter = image,
                contentDescription = stringResource(R.string.tasks_completed),
                alignment = Alignment.Center
            )
            TextTaskCompleted(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SecondChallengeTheme {
        TaskCompleted(Modifier.fillMaxSize())
    }
}