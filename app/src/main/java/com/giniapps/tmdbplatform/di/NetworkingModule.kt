package com.giniapps.tmdbplatform.di

import androidx.viewbinding.BuildConfig
import com.giniapps.tmdbplatform.BuildConfig.TMDB_API_KEY
import com.giniapps.tmdbplatform.BuildConfig.TMDB_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.giniapps.tmdbplatform.networking.MovieApi
import com.giniapps.tmdbplatform.networking.PersonApi
import com.giniapps.tmdbplatform.networking.TVShowApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {
    private val API_KEY_NAME = "api_key"
    private val BASE_URL = TMDB_BASE_URL
    private val API_KEY_VALUE =TMDB_API_KEY

    @Provides
    fun provideGsonFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideAuthorizationInterceptor() = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            if (API_KEY_VALUE.isBlank()) return chain.proceed(originalRequest)

            val url =
                originalRequest.url.newBuilder().addQueryParameter(API_KEY_NAME, API_KEY_VALUE)
                    .build()
            val newRequest = originalRequest.newBuilder().url(url).build()
            return chain.proceed(newRequest)
        }
    }


    @Provides
    fun providedOKHTTPClient(
        authInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor).build()


    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gsonConverterFactory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    fun provideTVService(retrofit: Retrofit): TVShowApi = retrofit.create(TVShowApi::class.java)

    @Provides
    fun providePersonService(retrofit: Retrofit): PersonApi = retrofit.create(PersonApi::class.java)

}
