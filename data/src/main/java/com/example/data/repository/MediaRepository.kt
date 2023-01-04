package com.example.data.repository

import com.example.data.commons.Output
import com.example.domain.Genre
import com.example.domain.MediaResult

interface MediaRepository {

    suspend fun getPopular(
        language: String,
        page: Int,
        mediaType: String
    ): Output<MediaResult>

    suspend fun getTrending(
        language: String,
        page: Int,
        mediaType: String,
        validTimeTrending: String
    ): Output<MediaResult>

    suspend fun getSimilar(
        language: String,
        page: Int,
        mediaType: String,
        mediaId: Int
    ): Output<MediaResult>

    suspend fun getGenres(
        language: String,
        mediaType: String
    ): Output<List<Genre>>
}
