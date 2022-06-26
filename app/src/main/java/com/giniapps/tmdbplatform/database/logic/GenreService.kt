package com.giniapps.tmdbplatform.database.logic

import com.giniapps.tmdbplatform.model.response.Genre

interface GenreService {
    suspend fun getAllGenres(): List<Genre>
    suspend fun getSpecificGenre(id: String): Genre
    suspend fun createGenre(genre: Genre)
    suspend fun createAllGenres(genres: List<Genre>)
    suspend fun deleteGenre(genre: Genre)
}