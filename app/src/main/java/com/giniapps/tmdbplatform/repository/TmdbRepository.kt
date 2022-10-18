package com.giniapps.tmdbplatform.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.giniapps.tmdbplatform.model.response.*

interface TmdbRepository {
    suspend fun getMoviesByCategory(id: String): CategoryWithMovies
    suspend fun getMovieWithGenreById(id: String): MovieWithGenres
    suspend fun getWatchList(): List<WatchListItem>
    fun getSearchResults(movieName: String): LiveData<PagingData<Media>>
    suspend fun getTrailerById(id: Long): VideoWrapper
    suspend fun getCasts(mediaItem: Media): List<Cast>
    suspend fun createItemWatchList(item: WatchListItem)
    suspend fun removeItemWatchList(item: WatchListItem)
    suspend fun getSpecificItem(currentMedia: Media): WatchListItem?

}