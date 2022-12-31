package com.example.usecases

import com.example.data.repository.MovieRepository

class GetMovies(private val repository: MovieRepository) {

    suspend operator fun invoke(language: String, page: Int) =
        repository.getMovies(language, page)

}