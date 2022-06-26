package com.giniapps.tmdbplatform.database.dal

import androidx.room.*
import com.giniapps.tmdbplatform.model.response.*

@Dao
interface TvShowDao {
    @Query("SELECT * FROM TV_SHOWS")
    suspend fun findAllTvShows(): List<TVShow>

    @Query("SELECT * FROM TV_SHOWS  ORDER BY releaseDate DESC")
    suspend fun findAllTvShowsByLatest(): List<TVShow>

    @Query("SELECT * FROM TV_SHOWS  ORDER BY voteAverage DESC")
    suspend fun findAllTvShowsByPopularity(): List<TVShow>

    @Query("SELECT * FROM TV_SHOWS WHERE id=:id")
    suspend fun findTvShowById(id: String): TvShowWithGenres

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShow(tvShow: TVShow)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllTvShows(tvShows: List<TVShow>)

    @Delete()
    suspend fun deleteTvShow(tvShow: TVShow)

    @Transaction
    @Query("SELECT * FROM TV_SHOWS WHERE name LIKE '%' || :tvShowName  || '%' ")
    suspend fun findAllTvShowsByName(tvShowName: String): List<TvShowWithGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTvShowGenreCrossRef(tvShowWithGenreCrossRef: TvShowWithGenreCrossRef)

    @Transaction
    @Query("SELECT * FROM TV_SHOWS WHERE id=:id")
    suspend fun findTvShowWithGenresById(id: String): TvShowWithGenres

    @Transaction
    @Query("SELECT * FROM MOVIES")
    suspend fun findAllTvShowsWithGenres(): List<TvShowWithGenres>

}