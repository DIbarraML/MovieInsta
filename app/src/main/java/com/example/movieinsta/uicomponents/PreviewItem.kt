package com.example.movieinsta.uicomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieinsta.ui.theme.MovieInstaTheme

@Composable
fun PreviewItem(url: String) {
    Box(
        modifier = Modifier
            .width(168.dp)
            .height(152.dp),

        ) {
       AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .transformations(CircleCropTransformation())
                .build(),
            contentDescription = "",
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
                .align(Alignment.Center)
        )
        Text(
            text = "STRANGER \n THINGS",
            modifier = Modifier
                .align(Alignment.BottomCenter),
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewItem() {
    MovieInstaTheme {
        val url = "https://i.pinimg.com/originals/e9/fa/e3/e9fae3dcae1a3b978adaf214cac3c607.jpg"
        PreviewItem(url)
    }
}