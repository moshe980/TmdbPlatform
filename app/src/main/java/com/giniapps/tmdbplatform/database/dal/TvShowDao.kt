package com.giniapps.tmdbplatform.database.dal

import androidx.room.*
import com.giniapps.tmdbplatform.database.data.TvShowEntity
import com.giniapps.tmdbplatform.model.response.TmdbItem

@Dao
interface TvShowDao {
    @Query("SELECT * FROM TV_SHOWS")
    fun findAllTvShows(): List<TvShowEntity>

    @Transaction
    @Query("SELECT * FROM TV_SHOWS WHERE id=:id")
    fun findTvShowById(id: String): TvShowEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTvShow(tvShowEntity: TvShowEntity)

    @Delete()
    fun deleteTvShow(tvShowEntity: TvShowEntity)
}