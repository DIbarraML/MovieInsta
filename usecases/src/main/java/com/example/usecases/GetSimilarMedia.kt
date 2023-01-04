package com.example.usecases

import com.example.data.repository.MediaRepository

class GetSimilarMedia(private val repository: MediaRepository) {

    suspend operator fun invoke(
        language: String,
        mediaType: String,
        mediaId: Int
    ) = repository.getSimilar(
        language = language,
        mediaType = mediaType,
        mediaId = mediaId
    )
}