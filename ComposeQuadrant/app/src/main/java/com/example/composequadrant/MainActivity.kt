package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Row (modifier = Modifier
                        .fillMaxSize()) {
                        CardBox(
                            title = stringResource(R.string.text_composable),
                            paragraph = stringResource(R.string.text_composable_text),
                            color = colorResource(R.color.shade_of_purple_intensity_1),
                            modifier = Modifier
                                .fillMaxSize(.5f)
                                .weight(0.5f)
                                .align(alignment = Alignment.Top)
                        )
                        CardBox(
                            title = stringResource(R.string.image_composable),
                            paragraph = stringResource(R.string.image_composable_text),
                            color = colorResource(R.color.shade_of_purple_intensity_2),
                            modifier = Modifier
                                .fillMaxSize(.5f)
                                .weight(0.5f)
                                .align(alignment = Alignment.Top)
                        )
                    }

                    Row (modifier = Modifier
                        .fillMaxSize()) {
                        CardBox(
                            title = stringResource(R.string.row_composable),
                            paragraph = stringResource(R.string.row_composable_text),
                            color = colorResource(R.color.shade_of_purple_intensity_3),
                            modifier = Modifier
                                .fillMaxSize(.5f)
                                .weight(0.5f)
                                .align(alignment = Alignment.Bottom)
                        )
                        CardBox(
                            title = stringResource(R.string.column_composable),
                            paragraph = stringResource(R.string.column_composable_text),
                            color = colorResource(R.color.shade_of_purple_intensity_4),
                            modifier = Modifier
                                .fillMaxSize(.5f)
                                .weight(0.5f)
                                .align(alignment = Alignment.Bottom)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CardBox(title: String, paragraph: String, color: Color, modifier: Modifier = Modifier) {
            Column(
                modifier
                    .background(color = color)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ){
            Text(
                text = title,
                color = colorResource(R.color.black),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 16.dp)

            )
            Text(
                text = paragraph,
                color = colorResource(R.color.black),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify
            )
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        Row (modifier = Modifier
            .fillMaxSize()) {
            CardBox(
                title = stringResource(R.string.text_composable),
                paragraph = stringResource(R.string.text_composable_text),
                color = colorResource(R.color.shade_of_purple_intensity_1),
                modifier = Modifier
                    .fillMaxSize(.5f)
                    .weight(0.5f)
                    .align(alignment = Alignment.Top)
            )
            CardBox(
                title = stringResource(R.string.image_composable),
                paragraph = stringResource(R.string.image_composable_text),
                color = colorResource(R.color.shade_of_purple_intensity_2),
                modifier = Modifier
                    .fillMaxSize(.5f)
                    .weight(0.5f)
                    .align(alignment = Alignment.Top)
            )
        }

        Row (modifier = Modifier
            .fillMaxSize()) {
            CardBox(
                title = stringResource(R.string.row_composable),
                paragraph = stringResource(R.string.row_composable_text),
                color = colorResource(R.color.shade_of_purple_intensity_3),
                modifier = Modifier
                    .fillMaxSize(.5f)
                    .weight(0.5f)
                    .align(alignment = Alignment.Bottom)
            )
            CardBox(
                title = stringResource(R.string.column_composable),
                paragraph = stringResource(R.string.column_composable_text),
                color = colorResource(R.color.shade_of_purple_intensity_4),
                modifier = Modifier
                    .fillMaxSize(.5f)
                    .weight(0.5f)
                    .align(alignment = Alignment.Bottom)
            )
        }
    }
}