package com.example.grid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grid.data.DataSource
import com.example.grid.model.Topic
import com.example.grid.ui.theme.GridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            GridTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   CoursesApp()
                }
            }
        }
    }
}

@Composable
fun CoursesApp() {
    GridList(
        DataSource.topics,
    )
}

@Composable
fun GridList(topicList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.black)),
        columns = GridCells.Adaptive(LocalConfiguration.current.screenWidthDp.dp / 2),
        content = {
            items(topicList) { topic ->
                ModelApp(topic, modifier
                    .fillMaxSize()
                    .padding(8.dp))
            }
        }
    )
}

@Composable
fun ModelApp(
    topic: Topic,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.purple_200))
    ) {
        Row {
            Image(
                painter = painterResource(topic.image) ,
                contentDescription = stringResource(topic.field),
                modifier = Modifier
                    .fillMaxHeight()
                    .background(colorResource(R.color.purple_200))
                    .width(68.dp),
                contentScale = ContentScale.Crop,
            )
            Column {
                Text(
                    text = stringResource(topic.field),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(16.dp, 16.dp, 16.dp),
                )
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = topic.count.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GridTheme {
        CoursesApp()
    }
}