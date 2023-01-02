package com.example.usecases

import com.example.data.repository.MediaRepository

class GetSimilarMedia(private val repository: MediaRepository) {

    suspend operator fun invoke(
        language: String,
        page: Int,
        mediaType: String,
        mediaId: Int
    ) = repository.getSimilar(
        language = language,
        page = page,
        mediaType = mediaType,
        mediaId = mediaId
    )
}