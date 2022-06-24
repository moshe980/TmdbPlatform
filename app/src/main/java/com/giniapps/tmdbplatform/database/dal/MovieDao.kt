package com.giniapps.tmdbplatform.database.dal

import androidx.room.*
import com.giniapps.tmdbplatform.database.data.MovieEntity
import com.giniapps.tmdbplatform.model.response.TmdbItem

@Dao//i want crud repository and jpa query methods
interface MovieDao {
    @Query("SELECT * FROM MOVIES")
    fun findAllMovies(): List<MovieEntity>

    @Transaction
    @Query("SELECT * FROM MOVIES WHERE id=:id")
    fun findMovieById(id: String): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movieEntity: MovieEntity)

    @Delete()
    fun deleteAllMovies(movieEntity: MovieEntity)
}