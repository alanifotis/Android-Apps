package com.example.superheroes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import model.Hero

@Composable
fun CardElement(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
    ) {
        Row (
            modifier = Modifier.padding(16.dp)
        ){
            NameElement(
                name = hero.superHeroName,
                description = hero.superHeroDescription,
                modifier = Modifier
                    .weight(0.8f)
                    .padding(end = 16.dp)
            )
            ImageElement(
                image = hero.superHeroImage
            )
        }
    }
}


@Composable
private fun NameElement(
    @StringRes name: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .height(72.dp)
    ) {
        Text(
            text = stringResource(name),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(description),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Composable
private fun ImageElement(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
){
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(72.dp)
                .width(72.dp)
                .clip(MaterialTheme.shapes.small)
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroesopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}