package com.example.data.remote

import android.content.Context
import com.example.data.ServiceFactory
import com.example.data.commons.Output
import com.example.data.model.MovieResultDTO
import com.example.data.service.MovieService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRemoteDataSource(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    context: Context? = null
) {

    private val service: MovieService =
        ServiceFactory.createRepositoryApi(
            repositoryApiClass = MovieService::class.java,
            context = context
        )

    suspend fun getMovies(
        language: String,
        page: Int
    ): Output<MovieResultDTO> {
        runCatching {
            withContext(dispatcher) {
                val result = service.getPopularMovies(
                    language = language,
                    page = page,
                    apiKey = "4184cd4bd710debe91877e74fa38c118"
                )
                result.body()
            }
        }.fold(
            onSuccess = { response ->
                return if (response != null) {
                    Output.Success(response)
                } else {
                    Output.Failure(Exception(MESSAGE_DEFAULT))
                }
            },
            onFailure = {
                return Output.Failure(it as Exception)
            }
        )
    }

    companion object {
        private const val MESSAGE_DEFAULT = "No response from server"
    }
}