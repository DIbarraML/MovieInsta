package com.example.movieinsta.uicomponents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.Genre
import com.example.domain.Movie
import com.example.movieinsta.R
import com.example.presentation.theme.MovieInstaTheme

@Composable
fun Slider(movie: Movie, listGenres: List<Genre>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.posterPath)
                .error(R.color.gray_light)
                .build(),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .applyGradient(),
            contentScale = ContentScale.Crop
        )

        InfoSlider(
            movie = movie,
            listGenres,
            Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun PaintPoint(modifier: Modifier) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = Color.White,
            radius = 10f
        )
    }
}

@Composable
private fun LabelsSlider(labels: List<Genre>, modifier: Modifier) {
    println("labels -> ${labels.size}")
    Row(
        modifier = modifier
    ) {
        labels.forEachIndexed { index, genres ->
            Text(text = genres.name, style = MaterialTheme.typography.h5)
            if (index != labels.size - 1) {
                PaintPoint(
                    Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .width(5.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}


@Composable
private fun InfoSlider(
    movie: Movie,
    listGenres: List<Genre>,
    modifier: Modifier
) {

    Column(modifier = modifier.padding(bottom = 32.dp)) {

        val modifierChild = Modifier
            .align(CenterHorizontally)
            .padding(bottom = 32.dp)

        Text(
            text = movie.title,
            modifier = modifierChild,
            style = MaterialTheme.typography.h2,
            fontSize = 36.sp,
            textAlign = TextAlign.Center
        )

        LabelsSlider(listGenres, modifierChild)

        Row(
            modifier = Modifier
                .width(300.dp)
                .height(64.dp)
                .align(CenterHorizontally),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyListButton(modifier = Modifier.weight(1f))
            PlayButton(Modifier.align(Alignment.Top))
            InfoButton(modifier = Modifier.weight(1f))
        }
    }


}

fun Modifier.applyGradient(): Modifier {
    return drawWithCache {
        val gradient = Brush.verticalGradient(
            colors = listOf(Color.Transparent, Color.Black),
            startY = size.height / 3,
            endY = size.height
        )
        onDrawWithContent {
            drawContent()
            drawRect(gradient, blendMode = BlendMode.Multiply)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewSliderItem() {
    MovieInstaTheme {
        //Slider("https://i.pinimg.com/originals/e9/fa/e3/e9fae3dcae1a3b978adaf214cac3c607.jpg")
    }
}