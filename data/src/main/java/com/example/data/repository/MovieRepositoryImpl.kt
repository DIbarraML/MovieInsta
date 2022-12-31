package com.example.data.repository

import com.example.data.commons.Output
import com.example.data.model.MovieResultDTO
import com.example.data.remote.MovieRemoteDataSource

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
    ): MovieRepository {

    override suspend fun getMovies(
        language: String,
        page: Int
    ): Output<MovieResultDTO> {
        return movieRemoteDataSource.getMovies(language, page)
    }
}
