package com.example.data.model

import com.example.domain.Movie
import com.example.domain.MovieResult
import com.google.gson.annotations.SerializedName

data class MovieDTO(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backDropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    fun asDomainModel() = Movie(
        id = id,
        isAdult = adult,
        backdropPath = backDropPath,
        posterPath = posterPath,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        title = title,
        overview = overview,
        popularity = popularity,
        releaseDate = releaseDate,
        isVideo = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

data class MovieResultDTO(
    val page: Int,
    @SerializedName("results")
    val moviesDTOS: List<MovieDTO>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    fun asDomainModel(): MovieResult {
        val listMovie = mutableListOf<Movie>()
        moviesDTOS.map {
            listMovie.add(it.asDomainModel())
        }
        return MovieResult(listMovie)
    }
}
