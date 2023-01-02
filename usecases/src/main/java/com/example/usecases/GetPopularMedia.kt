package com.example.usecases

import com.example.data.repository.MediaRepository

class GetPopularMedia(private val repository: MediaRepository) {

    suspend operator fun invoke(language: String, page: Int, mediaType: String) =
        repository.getPopular(language, page, mediaType)
}