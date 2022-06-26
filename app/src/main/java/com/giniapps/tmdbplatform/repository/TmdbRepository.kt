package com.giniapps.tmdbplatform.repository

import com.giniapps.tmdbplatform.model.response.*

interface TmdbRepository {
    suspend fun getMoviesByCategory(id: String): CategoryWithMovies
    suspend fun getLatestTvShow(): List<TVShow>
    suspend fun getPopularTvShows(): List<TVShow>
    suspend fun getMovieWithGenreById(id: String): MovieWithGenres
    suspend fun getTvShowWithGenreById(id: String): TvShowWithGenres
    suspend fun getWatchList(): List<WatchListItem>
    suspend fun findAllMoviesByName(movieName: String): List<MovieWithGenres>
    suspend fun findAllTvShowsByName(tvShowName: String): List<TvShowWithGenres>
}