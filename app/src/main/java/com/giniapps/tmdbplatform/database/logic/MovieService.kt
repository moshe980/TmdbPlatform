package com.giniapps.tmdbplatform.database.logic

import android.graphics.Movie
import com.giniapps.tmdbplatform.model.response.*

interface MediaService {
     suspend fun getAllMovies(): List<Media>
     suspend fun getSpecificMovie(id: String): MovieWithGenres
     suspend fun createMovie(movie: Media)
     suspend fun createAllMovies(movies: List<Media>)
     suspend   fun deleteMovie(movie: Media)
     suspend fun getMoviesWithGenres(): List<MovieWithGenres>
     suspend fun getAllLatestMovies(): List<Media>
     suspend fun getAllPopularMovies(): List<Media>
     suspend fun createMovieGenreCrossRef(movieWithGenreCrossRef: MovieWithGenreCrossRef)
     suspend fun getAllMoviesByName(movieName: String):List<MovieWithGenres>


}