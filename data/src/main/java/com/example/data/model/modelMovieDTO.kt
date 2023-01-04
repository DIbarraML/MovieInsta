package com.example.data.model

import com.example.domain.Media
import com.example.domain.MediaResult
import com.google.gson.annotations.SerializedName

data class MovieDTO(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backDropPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title", alternate = ["original_name"])
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName(value = "release_date", alternate = ["first_air_date"])
    val releaseDate: String,
    val title: String?,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val name: String?,
    val mediaType: String?
) {
    fun asDomainModel() = Media(
        id = id,
        isAdult = adult,
        backdropPath = EndPoints.IMAGE_BASE_URL + backDropPath,
        posterPath = EndPoints.IMAGE_BASE_URL + posterPath,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        title = title ?: name ?: "",
        overview = overview,
        popularity = popularity,
        releaseDate = releaseDate,
        isVideo = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        name = name,
        mediaType = mediaType ?: mediaTypeFromTitle()
    )

    private fun mediaTypeFromTitle() =
        if (title.isNullOrEmpty()) {
            "tv"
        } else {
            "movie"
        }

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
    fun asDomainModel(): MediaResult {
        val listMedia = mutableListOf<Media>()
        moviesDTOS.map {
            listMedia.add(it.asDomainModel())
        }
        return MediaResult(listMedia)
    }
}
