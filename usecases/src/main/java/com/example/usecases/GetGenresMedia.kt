package com.example.usecases

import com.example.data.repository.MediaRepository

class GetGenresMedia(private val repository: MediaRepository) {

    suspend operator fun invoke(
        language: String,
        mediaType: String
    ) =
        repository.getGenres(
            language = language,
            mediaType = mediaType
        )
}