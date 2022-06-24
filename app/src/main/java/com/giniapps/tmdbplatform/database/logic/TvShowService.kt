package com.giniapps.tmdbplatform.database.logic

import androidx.room.*
import com.giniapps.tmdbplatform.model.response.TmdbItem

interface TvShowService {
    fun getAllTvShows(): List<TmdbItem.TVShow>
    fun getSpecificTvShow(id: String): TmdbItem.TVShow
    fun createTvShow(tvShow: TmdbItem.TVShow)
    fun deleteAllShows(tvShow: TmdbItem.TVShow)
}