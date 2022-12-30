package com.example.movieinsta.uicomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieinsta.R
import com.example.movieinsta.ui.theme.MovieInstaTheme

@Composable
fun MovieCard(url: String) {
    Box(
        modifier = Modifier
            .height(184.dp)
            .width(128.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .error(R.color.gray_light)
                .build(),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewCard() {
    MovieInstaTheme {
        val url = "https://i.pinimg.com/originals/e9/fa/e3/e9fae3dcae1a3b978adaf214cac3c607.jpg"
        MovieCard(url)
    }
}