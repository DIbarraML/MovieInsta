package com.example.movieinsta

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.commons.Output
import com.example.data.model.MediaType
import com.example.data.model.ValidTypeTrending
import com.example.domain.Genre
import com.example.domain.Media
import com.example.usecases.GetGenresMedia
import com.example.usecases.GetPopularMedia
import com.example.usecases.GetSimilarMedia
import com.example.usecases.GetTrendingMedia
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(
    private val getPopularMedia: GetPopularMedia,
    private val getTrendingMedia: GetTrendingMedia,
    private val getSimilarMedia: GetSimilarMedia,
    private val getGenresMedia: GetGenresMedia
) : ViewModel() {

    val media = mutableStateListOf<List<Media>>()
    private var pageMovie = mutableStateOf(0)

    val seriesTv = mutableStateListOf<List<Media>>()
    private var pageTv = mutableStateOf(0)

    val seriesTrending = mutableStateListOf<List<Media>>()
    private var pageSeriesTrending = mutableStateOf(0)

    val moviesTrending = mutableStateListOf<List<Media>>()
    private var pageMoviesTrending = mutableStateOf(0)

    val genreMovie = mutableStateListOf<Genre>()

    val genreTv = mutableStateListOf<Genre>()


    fun getPopularMovies() {
        viewModelScope.launch {
            pageMovie.value++
            when (val output = getPopularMedia.invoke(
                language = LANG_ESP,
                page = pageMovie.value,
                mediaType = MediaType.MOVIE.name.lowercase(Locale.ROOT)
            )) {
                is Output.Success -> {
                    media.add(output.value.media)
                }
                is Output.Failure -> {
                }
            }
        }
    }

    fun getPopularTv() {
        viewModelScope.launch {
            pageTv.value++
            when (val output = getPopularMedia.invoke(
                language = LANG_ESP,
                page = pageTv.value,
                MediaType.TV.name.lowercase()
            )) {
                is Output.Success -> {
                    seriesTv.add(output.value.media)
                }
                is Output.Failure -> {
                }
            }
        }
    }

    fun getTrendingTv() {
        viewModelScope.launch {
            pageSeriesTrending.value++
            when (val output = getTrendingMedia.invoke(
                language = LANG_ESP,
                page = pageSeriesTrending.value,
                mediaType = MediaType.TV.name.lowercase(),
                validTypeTrending = ValidTypeTrending.DAY.name.lowercase()
            )) {
                is Output.Success -> {
                    seriesTrending.add(output.value.media)
                }
                is Output.Failure -> {
                }
            }
        }
    }

    fun getTrendingMovies() {
        viewModelScope.launch {
            pageMoviesTrending.value++
            when (val output = getTrendingMedia.invoke(
                language = LANG_ESP,
                page = pageMoviesTrending.value,
                mediaType = MediaType.MOVIE.name.lowercase(),
                validTypeTrending = ValidTypeTrending.DAY.name.lowercase()
            )) {
                is Output.Success -> {
                    moviesTrending.add(output.value.media)
                }
                is Output.Failure -> {
                }
            }
        }
    }

    fun getGenresTv() {
        viewModelScope.launch {
            pageMoviesTrending.value++
            when (val output = getGenresMedia.invoke(
                language = LANG_ESP,
                mediaType = MediaType.TV.name.lowercase(),
            )) {
                is Output.Success -> {
                    genreTv.addAll(output.value)
                }
                is Output.Failure -> {
                }
            }
        }
    }

    fun getGenresMovies() {
        viewModelScope.launch {
            pageMoviesTrending.value++
            when (val output = getGenresMedia.invoke(
                language = LANG_ESP,
                mediaType = MediaType.MOVIE.name.lowercase(),
            )) {
                is Output.Success -> {
                    genreMovie.addAll(output.value)
                }
                is Output.Failure -> {
                }
            }
        }
    }

    fun findGenresMoviesById(list: List<Int>): List<Genre> {
        val listGenre = mutableStateListOf<Genre>()
        list.map { id ->
            genreMovie.map { genre ->
                if (id == genre.id) {
                    listGenre.add(genre)
                }
            }
        }
        return listGenre
    }

    fun findGenresTvById(list: List<Int>): List<Genre> {
        val listGenre = mutableStateListOf<Genre>()
        list.map { id ->
            genreTv.map { genre ->
                if (id == genre.id) {
                    listGenre.add(genre)
                }
            }
        }
        return listGenre
    }

    fun getMediaSlider() = moviesTrending.random().random()

    companion object {
        private const val LANG_ESP = "es"
    }
}