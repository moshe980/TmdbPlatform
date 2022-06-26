package com.giniapps.tmdbplatform.networking

import com.giniapps.tmdbplatform.model.response.GenreWrapper
import retrofit2.http.GET

interface GenreApi {
    @GET("3/genre/movie/list?language=en-US")
    suspend fun genres(): GenreWrapper

}