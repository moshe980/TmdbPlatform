package com.giniapps.tmdbplatform.networking

import com.giniapps.tmdbplatform.model.response.*

//all the apis in one facade
interface RemoteApi {
    //Movies:
    suspend fun allMovies(page: Int = 1): Result<ItemWrapper<Movie>>
    suspend fun popularMovies(page: Int = 1): Result<ItemWrapper<Movie>>
    suspend fun topRatedMovies(page: Int = 1): Result<ItemWrapper<Movie>>
    suspend fun latestMovies(page: Int = 1): Result<ItemWrapper<Movie>>
    suspend fun searchMovies(page: Int = 1, query: String): Result<ItemWrapper<Movie>>
    suspend fun movieTrailers(movieId: Long): Result<VideoWrapper>
    suspend fun movieCredits(movieId: Long): Result<CreditWrapper>

    //TV:
    suspend fun allTvShows(page: Int = 1): Result<ItemWrapper<TVShow>>
    suspend fun popularTVShows(page: Int = 1): Result<ItemWrapper<TVShow>>
    suspend fun topRatedTVShows(page: Int = 1): Result<ItemWrapper<TVShow>>
    suspend fun latestTVShows(page: Int = 1): Result<ItemWrapper<TVShow>>
    suspend fun searchTVShows(page: Int = 1, query: String): Result<ItemWrapper<TVShow>>
    suspend fun tvShowTrailers(tvId: Int): Result<VideoWrapper>
    suspend fun tvShowCredits(tvId: Int): Result<CreditWrapper>

    //Person:
    suspend fun getPerson(personId: Int): Result<Person>

    //Genre:
    suspend fun getGenres(): Result<GenreWrapper>



}