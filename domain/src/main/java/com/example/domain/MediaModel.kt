package com.example.domain

data class Media(
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

data class MediaResult(
    val media: List<Media>
)

data class Genre(
    val id: Int,
    val name: String
)
