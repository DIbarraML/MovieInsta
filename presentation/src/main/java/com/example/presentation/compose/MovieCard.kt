package com.example.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.Movie
import com.example.presentation.R

@Composable
fun MovieCard(movie: Movie, onclick: (Movie) -> Unit) {
    Box(
        modifier = Modifier
            .height(184.dp)
            .width(128.dp)
            .padding(start = 8.dp, end = 8.dp)
            .clickable { onclick.invoke(movie) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.posterPath)
                .error(R.color.gray_light)
                .build(),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ListMovieCard(title: Int, list: List<List<Movie>>, onclick: (Movie) -> Unit) {
    Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.h4,
        modifier = Modifier.padding(8.dp)
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        list.map {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp)
            ) {
                items(it) { movieList ->
                    MovieCard(movie = movieList, onclick)
                }
            }
        }

    }
}
