package com.example.data.remote

import android.content.Context
import com.example.data.ServiceFactory
import com.example.data.commons.Output
import com.example.data.model.EndPoints.API_KEY
import com.example.data.service.MediaService
import com.example.domain.Genre
import com.example.domain.MediaResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRemoteDataSource(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    context: Context
) {

    private val service: MediaService =
        ServiceFactory.createRepositoryApi(
            repositoryApiClass = MediaService::class.java,
            context = context
        )

    suspend fun getPopularMedia(
        language: String,
        page: Int,
        mediaType: String
    ): Output<MediaResult> {
        runCatching {
            withContext(dispatcher) {
                val result = service.getPopularMedia(
                    mediaType = mediaType,
                    language = language,
                    page = page,
                    apiKey = API_KEY
                )
                result.body()
            }
        }.fold(
            onSuccess = { response ->
                return if (response != null) {
                    Output.Success(response.asDomainModel())
                } else {
                    Output.Failure(Exception(MESSAGE_DEFAULT))
                }
            },
            onFailure = {
                return Output.Failure(it as Exception)
            }
        )
    }

    suspend fun getTrendingMedia(
        language: String,
        page: Int,
        mediaType: String,
        validTimeTrending: String
    ): Output<MediaResult> {
        runCatching {
            withContext(dispatcher) {
                val result = service.getTrendingMedia(
                    language = language,
                    page = page,
                    apiKey = API_KEY,
                    mediaType = mediaType,
                    time_window = validTimeTrending
                )
                result.body()
            }
        }.fold(
            onSuccess = { response ->
                return if (response != null) {
                    Output.Success(response.asDomainModel())
                } else {
                    Output.Failure(Exception(MESSAGE_DEFAULT))
                }
            },
            onFailure = {
                return Output.Failure(it as Exception)
            }
        )
    }

    suspend fun getSimilarMedia(
        language: String,
        mediaType: String,
        mediaId: Int
    ): Output<MediaResult> {
        runCatching {
            withContext(dispatcher) {
                val result = service.getSimilarMedia(
                    language = language,
                    apiKey = API_KEY,
                    mediaType = mediaType,
                    mediaId = mediaId
                )
                result.body()
            }
        }.fold(
            onSuccess = { response ->
                return if (response != null) {
                    Output.Success(response.asDomainModel())
                } else {

                    Output.Failure(Exception(MESSAGE_DEFAULT))
                }
            },
            onFailure = {
                return Output.Failure(it as Exception)
            }
        )
    }

    suspend fun getGenresMedia(
        language: String,
        mediaType: String,
    ): Output<List<Genre>> {
        runCatching {
            withContext(dispatcher) {
                val result = service.getGenresMedia(
                    language = language,
                    apiKey = API_KEY,
                    mediaType = mediaType
                )
                result.body()
            }
        }.fold(
            onSuccess = { response ->
                return if (response != null) {
                    Output.Success(response.asDomainModel())
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