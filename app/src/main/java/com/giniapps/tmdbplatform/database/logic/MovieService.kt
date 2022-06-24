package com.giniapps.tmdbplatform.database.logic

import androidx.room.*
import com.giniapps.tmdbplatform.model.response.TmdbItem

interface MovieService {
    fun getAllMovies(): List<TmdbItem.Movie>
    fun getSpecificMovie(id: String): TmdbItem.Movie
    fun createMovie(movie: TmdbItem.Movie)
    fun deleteAllMovies(movie: TmdbItem.Movie)
}