package com.giniapps.tmdbplatform.networking

import android.graphics.Movie
import com.giniapps.tmdbplatform.model.response.*
import javax.inject.Inject

class RemoteApiImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val tvApi: TVShowApi,
    private val personApi: PersonApi,
    private val genreApi: GenreApi,
) : RemoteApi {

    override suspend fun allMovies(page: Int): Result<ItemWrapper<Media>> = try {
        Success(movieApi.allMovieItems(page))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun popularMovies(page: Int): Result<ItemWrapper<Media>> =
        try {
            Success(movieApi.popularItems(page))
        } catch (e: Throwable) {
            Failure(e)
        }


    override suspend fun topRatedMovies(page: Int): Result<ItemWrapper<Media>> = try {
        Success(movieApi.topRatedItems(page))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun latestMovies(page: Int): Result<ItemWrapper<Media>> = try {
        Success(movieApi.latestItems(page))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun searchMovies(
        page: Int,
        query: String
    ): Result<ItemWrapper<Media>> = try {
        Success(movieApi.searchItems(page, query))
    } catch (e: Throwable) {
        Failure(e)
    }


    override suspend fun movieTrailers(movieId: Long): Result<VideoWrapper> = try {
        Success(movieApi.movieTrailers(movieId))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun movieCredits(movieId: Long): Result<CreditWrapper> = try {
        Success(movieApi.movieCredit(movieId))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun searchMovieById(id: String): Result<Media> = try {
        Success(movieApi.searchMovieById(id))
    } catch (e: Throwable) {
        Failure(e)
    }
    override suspend fun allTvShows(page: Int): Result<ItemWrapper<Media>> =try {
            Success(tvApi.allTvShowItems(page))
        } catch (e: Throwable) {
            Failure(e)
        }



    override suspend fun popularTVShows(page: Int): Result<ItemWrapper<Media>> = try {
        Success(tvApi.popularItems(page))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun topRatedTVShows(page: Int): Result<ItemWrapper<Media>> = try {
        Success(tvApi.topRatedItems(page))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun latestTVShows(page: Int): Result<ItemWrapper<Media>> = try {
        Success(tvApi.latestItems(page))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun searchTVShows(
        page: Int,
        query: String
    ): Result<ItemWrapper<Media>> =
        try {
            Success(tvApi.searchItems(page, query))
        } catch (e: Throwable) {
            Failure(e)
        }

    override suspend fun tvShowTrailers(tvId: Int): Result<VideoWrapper> = try {
        Success(tvApi.tvTrailers(tvId))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun tvShowCredits(tvId: Int): Result<CreditWrapper> = try {
        Success(tvApi.tvCredit(tvId))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun getPerson(personId: Int): Result<Person> = try {
        Success(personApi.getPerson(personId))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun getGenres(): Result<GenreWrapper> = try {
        Success(genreApi.genres())
    } catch (e: Throwable) {
        Failure(e)
    }

}