package com.example.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.example.domain.Media
import com.example.presentation.R

@Composable
fun MovieCard(media: Media, onclick: (Media) -> Unit) {
    Box(
        modifier = Modifier
            .height(184.dp)
            .width(128.dp)
            .padding(start = 8.dp, end = 8.dp, top = 4.dp)
            .clickable { onclick.invoke(media) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(media.posterPath)
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
fun ReelMovieCard(title: Int?, list: List<List<Media>>, onclick: (Media) -> Unit) {
    title?.let {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(8.dp)
        )
    }

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
                    MovieCard(media = movieList, onclick)
                }
            }
        }

    }
}

@Composable
fun GridMovieCard(list: List<Media>, onclick: (Media) -> Unit) {
    println("GridMovieCard -> $list.size")
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(900.dp)
    ) {
        items(list) { media ->
            MovieCard(media, onclick)
        }
    }
}
