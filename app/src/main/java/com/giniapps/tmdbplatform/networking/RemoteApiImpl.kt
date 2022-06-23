package com.giniapps.tmdbplatform.networking

import com.giniapps.tmdbplatform.model.response.*
import javax.inject.Inject

class RemoteApiImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val tvApi: TVShowApi,
    private val personApi: PersonApi
) : RemoteApi {

    override suspend fun popularMovies(page: Int): Result<ItemWrapper<TmdbItem.Movie>> = try {
        Success(movieApi.popularItems(page))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun topRatedMovies(page: Int): Result<ItemWrapper<TmdbItem.Movie>> = try {
        Success(movieApi.topRatedItems(page))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun latestMovies(page: Int): Result<ItemWrapper<TmdbItem.Movie>> = try {
        Success(movieApi.latestItems(page))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun searchMovies(
        page: Int,
        query: String
    ): Result<ItemWrapper<TmdbItem.Movie>> = try {
        Success(movieApi.searchItems(page, query))
    } catch (e: Throwable) {
        Failure(e)
    }


    override suspend fun movieTrailers(movieId: Int): Result<VideoWrapper> = try {
        Success(movieApi.movieTrailers(movieId))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun movieCredits(movieId: Int): Result<CreditWrapper> = try {
        Success(movieApi.movieCredit(movieId))
    } catch (e: Throwable) {
        Failure(e)
    }


    override suspend fun popularTVShows(page: Int): Result<ItemWrapper<TmdbItem.TVShow>> = try {
        Success(tvApi.popularItems(page))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun topRatedTVShows(page: Int): Result<ItemWrapper<TmdbItem.TVShow>> = try {
        Success(tvApi.topRatedItems(page))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun latestTVShows(page: Int): Result<ItemWrapper<TmdbItem.TVShow>> = try {
        Success(tvApi.latestItems(page))
    } catch (e: Throwable) {
        Failure(e)
    }

    override suspend fun searchTVShows(
        page: Int,
        query: String
    ): Result<ItemWrapper<TmdbItem.TVShow>> =
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
}