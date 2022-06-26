package com.giniapps.tmdbplatform.database.dal

import androidx.room.*
import com.giniapps.tmdbplatform.model.response.*

@Dao//i want crud repository and jpa query methods
interface MovieDao {
    @Query("SELECT * FROM MOVIES")
    suspend fun findAllMovies(): List<Movie>

    @Query("SELECT * FROM MOVIES WHERE id=:id")
    suspend fun findMovieById(id: String): Movie

    @Query("SELECT * FROM MOVIES  ORDER BY releaseDate DESC")
    suspend fun findAllMoviesByLatest(): List<Movie>

    @Query("SELECT * FROM MOVIES  ORDER BY voteAverage DESC")
    suspend fun findAllMoviesByPopularity(): List<Movie>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllMovies(list: List<Movie>)

    @Delete()
    suspend fun deleteMovie(movie: Movie)

    @Transaction
    @Query("SELECT * FROM MOVIES")
    suspend fun findAllMoviesWithGenres(): List<MovieWithGenres>

    @Transaction
    @Query("SELECT * FROM GENRES")
    suspend fun getGenresWithMovies(): List<GenreWithMovies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieGenreCrossRef(movieWithGenreCrossRef: MovieWithGenreCrossRef)

    @Transaction
    @Query("SELECT * FROM MOVIES WHERE id=:id")
    suspend fun findMovieWithGenresById(id: String): MovieWithGenres

    @Transaction
    @Query("SELECT * FROM MOVIES WHERE name LIKE '%' || :movieName  || '%' ")
    suspend fun findAllMoviesByName(movieName: String): List<MovieWithGenres>


}