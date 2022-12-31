package com.example.data.service

import com.example.data.model.MovieResultDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
    ): Response<MovieResultDTO>
}