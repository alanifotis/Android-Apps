package com.android.amphibians.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.amphibians.network.Amphibians
import com.android.amphibians.R

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)

) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Success -> MainAppScreen(
            amphibiansUiState.amphibians, modifier.fillMaxWidth()
        )
        AmphibiansUiState.Error -> {}
        AmphibiansUiState.Loading ->{}
    }
}

@Composable
fun MainAppScreen(
    amphibians: List<Amphibians>,
    modifier: Modifier = Modifier
) {
    LazyColumn(

    ) {
       items(items = amphibians, key = {amphibian -> amphibian.name }) { amphibian ->
           AmphibiansCard(
               amphibian,
               modifier = modifier.fillMaxWidth()
           )

       }
    }
}

@Composable
fun AmphibiansCard(
    amphibian: Amphibians,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(top = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Text(
            text = "${amphibian.name}(${amphibian.type})",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
            .data(amphibian.imgSrc)
            .crossfade(true)
            .build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            contentDescription = stringResource(id = R.string.amphibian_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = amphibian.description,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
