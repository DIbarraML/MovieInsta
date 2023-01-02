package com.example.data.model

import com.example.domain.Genre

data class GenreDTO(
    val id: Int,
    val name: String
) {
    fun asDomainModel() = Genre(
        id = id,
        name = name
    )
}

data class GenresResultDTO(
    val genres: List<GenreDTO>
) {
    fun asDomainModel(): List<Genre> {
        val genres = mutableListOf<Genre>()
        this.genres.map {
            genres.add(it.asDomainModel())
        }
        return genres
    }
}