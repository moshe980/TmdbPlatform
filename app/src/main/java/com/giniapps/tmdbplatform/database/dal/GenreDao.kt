package com.giniapps.tmdbplatform.database.dal

import androidx.room.*
import com.giniapps.tmdbplatform.model.response.Genre

@Dao
interface GenreDao {
    @Query("SELECT * FROM GENRES")
    suspend fun findAllGenres(): List<Genre>

    @Transaction
    @Query("SELECT * FROM GENRES WHERE genreId=:id")
    suspend fun findGenreById(id: String): Genre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun saveGenre(genre: Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllGenre(genres: List<Genre>)

    @Delete()
    suspend fun deleteGenre(genre: Genre)



}