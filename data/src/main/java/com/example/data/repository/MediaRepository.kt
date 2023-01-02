package com.example.data.repository

import com.example.data.commons.Output
import com.example.domain.Genre
import com.example.domain.MovieResult

interface MediaRepository {

    suspend fun getPopular(
        language: String,
        page: Int,
        mediaType: String
    ): Output<MovieResult>

    suspend fun getTrending(
        language: String,
        page: Int,
        mediaType: String,
        validTimeTrending: String
    ): Output<MovieResult>

    suspend fun getSimilar(
        language: String,
        page: Int,
        mediaType: String,
        mediaId: Int
    ): Output<MovieResult>

    suspend fun getGenres(
        language: String,
        mediaType: String
    ): Output<List<Genre>>
}
