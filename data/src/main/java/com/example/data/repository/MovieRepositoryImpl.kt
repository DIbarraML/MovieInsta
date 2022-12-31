package com.example.data.repository

import com.example.data.commons.Output
import com.example.data.remote.MovieRemoteDataSource
import com.example.domain.MovieResult

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getMovies(
        language: String,
        page: Int
    ): Output<MovieResult> =
        movieRemoteDataSource.getMovies(language, page)
}
