package com.giniapps.tmdbplatform.networking

import com.giniapps.tmdbplatform.model.response.CreditWrapper
import com.giniapps.tmdbplatform.model.response.ItemWrapper
import com.giniapps.tmdbplatform.model.response.TmdbItem
import com.giniapps.tmdbplatform.model.response.VideoWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    suspend fun popularItems(@Query("page") page: Int = 1): ItemWrapper<TmdbItem.Movie>

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    suspend fun topRatedItems(@Query("page") page: Int = 1): ItemWrapper<TmdbItem.Movie>

    @GET("3/movie/upcoming?language=en")
    suspend fun latestItems(@Query("page") page: Int = 1): ItemWrapper<TmdbItem.Movie>

    @GET("3/search/movie?language=en")
    suspend fun searchItems(
        @Query("page") page: Int = 1,
        @Query("query") query: String
    ): ItemWrapper<TmdbItem.Movie>

    @GET("3/movie/{movieId}/videos")
    suspend fun movieTrailers(@Path("movieId") movieId: Int = 1): VideoWrapper

    @GET("3/movie/{movieId}/credits")
    suspend fun movieCredit(@Path("movieId") movieId: Int = 1): CreditWrapper
}

//FACADE (TVSHOWAPI + MOVIEAPI + IMAGEAPI)

//url: https://api.themoviedb.org
//endpoint:  /3/discover/movie
// query string www form url encoded
// ?api_key=b3b1492d3e91e9f9403a2989f3031b0c&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate