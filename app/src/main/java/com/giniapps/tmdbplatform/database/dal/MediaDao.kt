package com.giniapps.tmdbplatform.database.dal

import android.graphics.Movie
import androidx.room.*
import com.giniapps.tmdbplatform.model.response.*

@Dao//i want crud repository and jpa query methods
interface MediaDao {
    @Query("SELECT * FROM MEDIAS")
    suspend fun findAllMovies(): List<Media>

    @Query("SELECT * FROM MEDIAS WHERE id=:id")
    suspend fun findMovieById(id: String): Media

    @Query("SELECT * FROM MEDIAS  ORDER BY releaseDate DESC")
    suspend fun findAllMoviesByLatest(): List<Media>

    @Query("SELECT * FROM MEDIAS  ORDER BY voteAverage DESC")
    suspend fun findAllMoviesByPopularity(): List<Media>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: Media)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllMovies(list: List<Media>)

    @Delete()
    suspend fun deleteMovie(movie: Media)

    @Transaction
    @Query("SELECT * FROM MEDIAS")
    suspend fun findAllMoviesWithGenres(): List<MovieWithGenres>

    @Transaction
    @Query("SELECT * FROM GENRES")
    suspend fun getGenresWithMovies(): List<GenreWithMovies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieGenreCrossRef(movieWithGenreCrossRef: MovieWithGenreCrossRef)

    @Transaction
    @Query("SELECT * FROM MEDIAS WHERE id=:id")
    suspend fun findMovieWithGenresById(id: String): MovieWithGenres

    @Transaction
    @Query("SELECT * FROM MEDIAS WHERE name LIKE '%' || :movieName  || '%' ")
    suspend fun findAllMoviesByName(movieName: String): List<MovieWithGenres>


}