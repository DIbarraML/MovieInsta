package com.example.data.model

import com.example.domain.MediaVideo

data class MediaVideosDto(
    val results: List<MediaVideoItemDto>
)

data class MediaVideoItemDto(
    val key: String,
    val name: String,
    val size: Int,
    val type: String
) {

    fun asDomainModel() = MediaVideo(
        key = key,
        url = "https://www.youtube.com/watch?v=$key",
        name = name,
        size = size,
        type = type
    )
}