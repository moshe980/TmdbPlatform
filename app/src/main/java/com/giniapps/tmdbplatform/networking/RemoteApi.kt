package com.giniapps.tmdbplatform.networking

import com.giniapps.tmdbplatform.model.response.*

//all the apis in one facade
interface RemoteApi {
    //Movies:

    suspend fun popularMovies(page: Int = 1): Result<ItemWrapper<TmdbItem.Movie>>
    suspend fun topRatedMovies(page: Int = 1): Result<ItemWrapper<TmdbItem.Movie>>
    suspend fun latestMovies(page: Int = 1): Result<ItemWrapper<TmdbItem.Movie>>
    suspend fun searchMovies(page: Int = 1, query: String): Result<ItemWrapper<TmdbItem.Movie>>
    suspend fun movieTrailers(movieId: Int): Result<VideoWrapper>
    suspend fun movieCredits(movieId: Int): Result<CreditWrapper>

    //TV:
    suspend fun popularTVShows(page: Int = 1): Result<ItemWrapper<TmdbItem.TVShow>>
    suspend fun topRatedTVShows(page: Int = 1): Result<ItemWrapper<TmdbItem.TVShow>>
    suspend fun latestTVShows(page: Int = 1): Result<ItemWrapper<TmdbItem.TVShow>>
    suspend fun searchTVShows(page: Int = 1, query: String): Result<ItemWrapper<TmdbItem.TVShow>>
    suspend fun tvShowTrailers(tvId: Int): Result<VideoWrapper>
    suspend fun tvShowCredits(tvId: Int): Result<CreditWrapper>

    //Person:
    suspend fun getPerson(personId: Int): Result<Person>
}