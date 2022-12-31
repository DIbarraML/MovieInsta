package com.example.data.repository

import com.example.data.commons.Output
import com.example.data.model.MovieResultDTO

interface MovieRepository {

    suspend fun getMovies(
        language: String,
        page: Int
    ): Output<MovieResultDTO>
}
