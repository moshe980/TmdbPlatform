package com.giniapps.tmdbplatform.repository

import android.content.SharedPreferences
import com.giniapps.tmdbplatform.database.logic.TmdbDataSource
import com.giniapps.tmdbplatform.model.response.*
import com.giniapps.tmdbplatform.networking.RemoteApi
import java.util.*
import javax.inject.Inject

class TmdbRepositoryLogic @Inject constructor() :TmdbRepository{
    @Inject
    lateinit var dataSource: TmdbDataSource

    @Inject
    lateinit var remoteApi: RemoteApi

    @Inject
    lateinit var sp: SharedPreferences
    private val spName = "last_update"


    private suspend fun getPopularMoviesFromApi() {
        val currentDate = System.currentTimeMillis()
        sp.edit().putLong(spName, currentDate).apply()
        val categoryId = "0"

        dataSource.createCategory(Category(categoryId, "Popular Movies"))

        val moviesResult = remoteApi.popularMovies(1)
        val genresResult = remoteApi.getGenres()
        // val tvShowsResult = remoteApi.allTvShows(1)

        if (moviesResult is Success && genresResult is Success) {
            val movies = moviesResult.data.items
            val genres = genresResult.data.genres

            dataSource.createAllMovies(movies)
            dataSource.createAllGenres(genres)

            movies.forEach { movie ->
                dataSource.createCategoryWithMoviesCrossRef(
                    CategoryWithMoviesCrossRef(
                        categoryId,
                        movie.id
                    )
                )
                movie.genreIds.forEach { genreId ->
                    dataSource.createMovieGenreCrossRef(
                        MovieWithGenreCrossRef(
                            movie.id,
                            genreId.toLong()
                        )
                    )
                }
            }

        } else {
            // return exc()

        }


    }

    private suspend fun getLatestMoviesFromApi() {
        val currentDate = System.currentTimeMillis()
        sp.edit().putLong(spName, currentDate).apply()
        val categoryId = "1"

        dataSource.createCategory(Category(categoryId, "Latest Movies"))

        val moviesResult = remoteApi.latestMovies(1)
        val genresResult = remoteApi.getGenres()
        // val tvShowsResult = remoteApi.allTvShows(1)

        if (moviesResult is Success && genresResult is Success) {
            val movies = moviesResult.data.items
            val genres = genresResult.data.genres

            dataSource.createAllMovies(movies)
            dataSource.createAllGenres(genres)

            movies.forEach { movie ->
                dataSource.createCategoryWithMoviesCrossRef(
                    CategoryWithMoviesCrossRef(
                        categoryId,
                        movie.id
                    )
                )
                movie.genreIds.forEach { genreId ->
                    dataSource.createMovieGenreCrossRef(
                        MovieWithGenreCrossRef(
                            movie.id,
                            genreId.toLong()
                        )
                    )
                }
            }

        } else {
            // return exc()

        }


    }

    private suspend fun getTopRatedMoviesFromApi() {
        val currentDate = System.currentTimeMillis()
        sp.edit().putLong(spName, currentDate).apply()
        val categoryId = "2"

        dataSource.createCategory(Category(categoryId, "Top rated Movies"))

        val moviesResult = remoteApi.topRatedMovies(1)
        val genresResult = remoteApi.getGenres()

        if (moviesResult is Success && genresResult is Success) {
            val movies = moviesResult.data.items
            val genres = genresResult.data.genres

            dataSource.createAllMovies(movies)
            dataSource.createAllGenres(genres)

            movies.forEach { movie ->
                dataSource.createCategoryWithMoviesCrossRef(
                    CategoryWithMoviesCrossRef(
                        categoryId,
                        movie.id
                    )
                )
                movie.genreIds.forEach { genreId ->
                    dataSource.createMovieGenreCrossRef(
                        MovieWithGenreCrossRef(
                            movie.id,
                            genreId.toLong()
                        )
                    )
                }
            }

        } else {
            // return exc()

        }


    }

    private fun isTimeForFetchFromApi(): Boolean {
        sp.edit().clear().apply()

        val ONE_DAY = 1000L * 60 * 60 * 24
        val now = System.currentTimeMillis()

        val lastUpdate = sp.getLong(spName, 0)

        if ((lastUpdate + ONE_DAY * 7) <= now) {
            return true

        } else if (lastUpdate == 0L) {
            return true
        }
        return false

    }


    override suspend fun getMoviesByCategory(id: String): CategoryWithMovies {
        getLatestMoviesFromApi()
        getTopRatedMoviesFromApi()
        getPopularMoviesFromApi()

        return dataSource.getSpecificCategory(id)
    }
    override suspend fun getLatestTvShow(): List<TVShow> = dataSource.getAllLatestTvShow()
    override suspend fun getPopularTvShows(): List<TVShow> = dataSource.getAllPopularTvShow()
    override suspend fun getMovieWithGenreById(id: String): MovieWithGenres = dataSource.getSpecificMovie(id)
    override suspend fun getTvShowWithGenreById(id: String): TvShowWithGenres =
        dataSource.getSpecificTvShow(id)
    override suspend fun getWatchList(): List<WatchListItem> = dataSource.getAllItems()
    override suspend fun findAllMoviesByName(movieName: String): List<MovieWithGenres> {

        val searchMovieResult = remoteApi.searchMovies(1, movieName)

        if (searchMovieResult is Success) {
            return searchMovieResult.data.items.map {
                MovieWithGenres(
                    it,
                    it.genreIds.map {
                        Genre(
                            it.toLong(),
                            dataSource.getSpecificGenre("$it").name
                        )
                    })
            }
        } else {
            return dataSource.getAllMoviesByName(movieName)

        }
    }
    override suspend fun findAllTvShowsByName(tvShowName: String): List<TvShowWithGenres> {
        return dataSource.getAllTvShowsByName(tvShowName)

    }


}