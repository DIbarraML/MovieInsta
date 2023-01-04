package com.example.movieinsta.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieinsta.R
import com.example.presentation.theme.MovieInstaTheme

@Composable
fun WatchingItem(url: String) {
    Column(
        modifier = Modifier
            .width(128.dp)
            .height(232.dp)
    ) {
        Box(
            modifier = Modifier.height(184.dp)
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

            Image(
                painter = painterResource(id = R.drawable.icon_play),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Column(
            modifier = Modifier
                .background(colorResource(id = R.color.gray_bar))
                .fillMaxWidth()
                .height(48.dp)
        ) {
            LinearProgressIndicator(
                progress = 0.5f,
                backgroundColor = colorResource(id = R.color.background_bar_progress),
                color = Color.Red
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.info_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .align(Alignment.CenterStart),
                    tint = Color.White
                )

                Icon(
                    painter = painterResource(id = R.drawable.main),
                    contentDescription = "",
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .align(Alignment.BottomEnd),
                    tint = Color.White
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewWatchingItem() {
    MovieInstaTheme {
        val url = "https://i.pinimg.com/originals/e9/fa/e3/e9fae3dcae1a3b978adaf214cac3c607.jpg"
        WatchingItem(url)
    }
}