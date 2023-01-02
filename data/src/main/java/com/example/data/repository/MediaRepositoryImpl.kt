package com.example.data.repository

import com.example.data.commons.Output
import com.example.data.remote.MovieRemoteDataSource
import com.example.domain.Genre
import com.example.domain.MovieResult

class MediaRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MediaRepository {

    override suspend fun getPopular(
        language: String,
        page: Int,
        mediaType: String
    ): Output<MovieResult> =
        movieRemoteDataSource.getPopularMedia(
            language = language,
            page = page,
            mediaType = mediaType
        )

    override suspend fun getTrending(
        language: String,
        page: Int,
        mediaType: String,
        validTimeTrending: String
    ): Output<MovieResult> =
        movieRemoteDataSource.getTrendingMedia(
            language = language,
            page = page,
            mediaType = mediaType,
            validTimeTrending = validTimeTrending
        )

    override suspend fun getSimilar(
        language: String,
        page: Int,
        mediaType: String,
        mediaId: Int
    ): Output<MovieResult> =
        movieRemoteDataSource.getSimilarMedia(
            language = language,
            page = page,
            mediaType = mediaType,
            mediaId = mediaId
        )

    override suspend fun getGenres(language: String, mediaType: String): Output<List<Genre>> =
        movieRemoteDataSource.getGenresMedia(
            language = language,
            mediaType = mediaType
        )

}
