package com.example.firstchallange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstchallange.ui.theme.FirstChallangeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstChallangeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FirstChallangeTheme {
                        ImagePageConstructor(Modifier
                            .fillMaxSize())
                    }
                }
            }
        }
    }
}

@Composable
fun TextPageConstructor( articleTitle: String, firstParagraph: String, secondParagraph: String, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = articleTitle,
            fontSize = 24.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(16.dp)

        )
        Text(
            text = firstParagraph,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Text(
            text = secondParagraph,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ImagePageConstructor(modifier: Modifier = Modifier) {
    val articleTitle = stringResource(R.string.article_title)
    val firstParagraph = stringResource(R.string.first_paragraph)
    val secondParagraph = stringResource(R.string.second_paragraph)
    val articleImage = painterResource(R.drawable.bg_compose_background)

    Box(modifier) {
        Column {
            Image(
                painter = articleImage,
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            TextPageConstructor(
                articleTitle = articleTitle,
                firstParagraph = firstParagraph,
                secondParagraph = secondParagraph,
                modifier = modifier
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstChallangeTheme {
        ImagePageConstructor(Modifier
            .fillMaxSize())
    }
}