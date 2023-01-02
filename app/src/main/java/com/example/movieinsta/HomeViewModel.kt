package com.example.movieinsta

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.commons.Output
import com.example.data.model.MediaType
import com.example.data.model.ValidTypeTrending
import com.example.domain.Genre
import com.example.domain.Movie
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

    val movies = mutableStateListOf<List<Movie>>()
    private var pageMovie = mutableStateOf(0)

    val seriesTv = mutableStateListOf<List<Movie>>()
    private var pageTv = mutableStateOf(0)

    val seriesTrending = mutableStateListOf<List<Movie>>()
    private var pageSeriesTrending = mutableStateOf(0)

    val moviesTrending = mutableStateListOf<List<Movie>>()
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
                    movies.add(output.value.movies)
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
                    seriesTv.add(output.value.movies)
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
                    seriesTrending.add(output.value.movies)
                }
                is Output.Failure -> {
                    println("TRENDING FAILURE")
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
                    println("SUCCES MOVIES -> ${output.value}")
                    moviesTrending.add(output.value.movies)
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
                    println("getGenresTv")
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
                    println("getGenresMovies")
                    genreMovie.addAll(output.value)
                }
                is Output.Failure -> {
                    println("getGenresMovies failure ->")
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