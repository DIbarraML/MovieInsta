package com.example.usecases

import com.example.data.repository.MediaRepository

class GetTrendingMedia(private val repository: MediaRepository) {

    suspend operator fun invoke(
        language: String,
        page: Int,
        mediaType: String,
        validTypeTrending: String
    ) =
        repository.getTrending(
            language = language,
            page = page,
            mediaType = mediaType,
            validTimeTrending = validTypeTrending
        )
}