package com.example.domain

data class Movie(
    val isAdult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val isVideo: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val name: String?,
    val mediaType: String
)

data class MovieResult(
    val movies: List<Movie>
)

data class Genre(
    val id: Int,
    val name: String
)
