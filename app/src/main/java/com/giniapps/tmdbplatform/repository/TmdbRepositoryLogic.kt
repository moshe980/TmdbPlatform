package com.giniapps.tmdbplatform.repository

import android.content.SharedPreferences
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.giniapps.tmdbplatform.MediaCategoryEnum
import com.giniapps.tmdbplatform.database.logic.TmdbDataSource
import com.giniapps.tmdbplatform.model.response.*
import com.giniapps.tmdbplatform.networking.RemoteApi
import com.giniapps.tmdbplatform.ui.searchMedia.MediaPagingSource
import javax.inject.Inject

class TmdbRepositoryLogic @Inject constructor() : TmdbRepository {
    @Inject
    lateinit var dataSource: TmdbDataSource

    @Inject
    lateinit var remoteApi: RemoteApi

    @Inject
    lateinit var sp: SharedPreferences

    private val spName = "last_update"


    private suspend fun getMediaByCategory(
        categoryId: String,
        categoryName: String,
        block: suspend () -> Result<ItemWrapper<Media>>
    ) {

        dataSource.createCategory(Category(categoryId, categoryName))

        val moviesResult = block()

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


    private fun isTimeForFetchFromApi(): Boolean {
        sp.edit().clear().apply()

        val oneDay = 1000L * 60 * 60 * 24
        val now = System.currentTimeMillis()

        val lastUpdate = sp.getLong(spName, 0)

        if ((lastUpdate + oneDay * 7) <= now) {
            return true

        } else if (lastUpdate == 0L) {
            return true
        }
        return false

    }


    override suspend fun getMoviesByCategory(id: String): CategoryWithMovies {
        getMediaByCategory(
            "0",
            MediaCategoryEnum.POPULAR_MOVIES.value
        ) { remoteApi.popularMovies(1) }
        getMediaByCategory("1", MediaCategoryEnum.LATEST_MOVIES.value) { remoteApi.latestMovies(1) }
        getMediaByCategory(
            "2",
            MediaCategoryEnum.TOP_RATED_MOVIES.value
        ) { remoteApi.topRatedMovies(1) }

        getMediaByCategory(
            "3",
            MediaCategoryEnum.POPULAR_TV_SHOWS.value
        ) { remoteApi.popularTVShows(1) }
        getMediaByCategory(
            "4",
            MediaCategoryEnum.LATEST_TV_SHOWS.value
        ) { remoteApi.latestTVShows(1) }
        getMediaByCategory(
            "5",
            MediaCategoryEnum.TOP_RATED_TV_SHOWS.value
        ) { remoteApi.topRatedTVShows(1) }

        return dataSource.getSpecificCategory(id)
    }

    override suspend fun getMovieWithGenreById(id: String): MovieWithGenres {
        var c = dataSource.getSpecificMovie(id)

        if (c != null) {
            return c
        } else {
            val movieResult = remoteApi.searchMovieById(id)
            if (movieResult is Success) {
                val m = movieResult.data

                return MovieWithGenres(m, emptyList())
            }
        }

        return c

    }

    override suspend fun getTrailerById(id: Long): VideoWrapper {
        val movieResult = remoteApi.movieTrailers(id)
        return if (movieResult is Success) {
            movieResult.data

        } else {
            val tvResult = remoteApi.tvShowTrailers(id.toInt())
            if (tvResult is Success) {
                tvResult.data
            } else {
                VideoWrapper(emptyList())
            }
        }

    }

    override suspend fun getCasts(mediaItem: Media): List<Cast> {
        val result = remoteApi.movieCredits(mediaItem.id)
        if (result is Success) {
            return result.data.cast.take(10)
        } else if (result is Failure) {
//            _hasServerConnection.value = result.exc?.message
//            _hasServerConnection.postValue(_hasServerConnection.value)
            return emptyList()

        }
        return emptyList()

    }

    override suspend fun createItemWatchList(item: WatchListItem) {
        dataSource.createItem(item)
    }

    override suspend fun removeItemWatchList(item: WatchListItem) {
        dataSource.deleteItem(item)
    }

    override suspend fun getSpecificItem(currentMedia: Media): WatchListItem {
        return dataSource.getSpecificItem("${currentMedia.id}")

    }


    override suspend fun getWatchList(): List<WatchListItem> = dataSource.getAllItems()

    override fun getSearchResults(movieName: String) =
        Pager(
            config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
            pagingSourceFactory = { MediaPagingSource(remoteApi, movieName) }).liveData


}