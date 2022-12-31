package com.example.data.repository

import com.example.data.commons.Output
import com.example.domain.MovieResult

interface MovieRepository {

    suspend fun getMovies(
        language: String,
        page: Int
    ): Output<MovieResult>
}
