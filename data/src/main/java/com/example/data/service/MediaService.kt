package com.example.data.service

import com.example.data.model.GenresResultDTO
import com.example.data.model.MovieResultDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MediaService {

    @GET("{media_type}/popular")
    suspend fun getPopularMedia(
        @Path("media_type") mediaType: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieResultDTO>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingMedia(
        @Path("media_type") mediaType: String,
        @Path("time_window") time_window: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieResultDTO>

    @GET("{media_type}/{media_id}/similar")
    suspend fun getSimilarMedia(
        @Path("media_type") mediaType: String,
        @Path("media_id") mediaId: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Response<MovieResultDTO>

    @GET("genre/{media_type}/list")
    suspend fun getGenresMedia(
        @Path("media_type") mediaType: String,
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Response<GenresResultDTO>
}