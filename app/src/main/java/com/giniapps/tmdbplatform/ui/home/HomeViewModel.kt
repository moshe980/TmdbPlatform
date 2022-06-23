package com.giniapps.tmdbplatform.ui.home

import android.app.Application
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.giniapps.tmdbplatform.R
import com.giniapps.tmdbplatform.model.response.Failure
import com.giniapps.tmdbplatform.model.response.MediaCategory
import com.giniapps.tmdbplatform.model.response.Success
import com.giniapps.tmdbplatform.networking.RemoteApi
import com.giniapps.tmdbplatform.ui.searchMedia.MediaInfoAdapter
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    @Inject
    lateinit var remoteApi: RemoteApi

    @Inject
    lateinit var categoryAdapter: CategoryAdapter

    private val _hasServerConnection = MutableLiveData<String>()
    val hasServerConnection: LiveData<String> get() = _hasServerConnection


    fun getMoviesFromApi() {
        val tmpList = mutableListOf<MediaCategory>()
        viewModelScope.launch {
            getPopularMovies(tmpList)
            getLatestMovies(tmpList)
            getTopRatedMovies(tmpList)

            getPopularTvShows(tmpList)
            getLatestTvShows(tmpList)
            getTopRatedTvShows(tmpList)


        }
    }

    private suspend fun getPopularMovies(tmpList: MutableList<MediaCategory>) {
        val result = remoteApi.popularMovies()
        if (result is Success) {
            tmpList.add(
                MediaCategory(
                    UUID.randomUUID(), getApplication<Application>().resources.getString(
                        R.string.popular_movies
                    ), result.data.items
                )
            )

        } else if (result is Failure) {
            _hasServerConnection.value = result.exc?.message
            _hasServerConnection.postValue(_hasServerConnection.value)
        }
    }

    private suspend fun getLatestMovies(tmpList: MutableList<MediaCategory>) {
        val result = remoteApi.latestMovies()
        if (result is Success) {
            tmpList.add(
                MediaCategory(
                    UUID.randomUUID(),
                    getApplication<Application>().resources.getString(R.string.latest_movies),
                    result.data.items
                )
            )
        } else if (result is Failure) {
            _hasServerConnection.value = result.exc?.message
            _hasServerConnection.postValue(_hasServerConnection.value)
        }
    }

    private suspend fun getTopRatedMovies(tmpList: MutableList<MediaCategory>) {
        val result = remoteApi.topRatedMovies()
        if (result is Success) {
            tmpList.add(
                MediaCategory(
                    UUID.randomUUID(),
                    getApplication<Application>().resources.getString(R.string.top_rated_movies),
                    result.data.items
                )
            )
        } else if (result is Failure) {
            _hasServerConnection.value = result.exc?.message
            _hasServerConnection.postValue(_hasServerConnection.value)
        }
    }

    private suspend fun getPopularTvShows(tmpList: MutableList<MediaCategory>) {
        val result = remoteApi.popularTVShows()
        if (result is Success) {
            tmpList.add(
                MediaCategory(
                    UUID.randomUUID(),
                    getApplication<Application>().resources.getString(R.string.popular_tv_shows),
                    result.data.items
                )
            )
        } else if (result is Failure) {
            _hasServerConnection.value = result.exc?.message
            _hasServerConnection.postValue(_hasServerConnection.value)
        }
    }

    private suspend fun getLatestTvShows(tmpList: MutableList<MediaCategory>) {
        val result = remoteApi.latestTVShows()
        if (result is Success) {
            tmpList.add(
                MediaCategory(
                    UUID.randomUUID(),
                    getApplication<Application>().resources.getString(R.string.latest_tv_shows),
                    result.data.items
                )
            )
        } else if (result is Failure) {
            _hasServerConnection.value = result.exc?.message
            _hasServerConnection.postValue(_hasServerConnection.value)
        }
    }

    private suspend fun getTopRatedTvShows(tmpList: MutableList<MediaCategory>) {
        val result = remoteApi.topRatedTVShows()
        if (result is Success) {
            tmpList.add(
                MediaCategory(
                    UUID.randomUUID(),
                    getApplication<Application>().resources.getString(R.string.top_rated_tv_shows),
                    result.data.items
                )

            )
            categoryAdapter.setData(tmpList)
        } else if (result is Failure) {
            _hasServerConnection.value = result.exc?.message
            _hasServerConnection.postValue(_hasServerConnection.value)
        }
    }

    fun getAdapter(): CategoryAdapter = categoryAdapter
    fun getViewPool(): RecyclerView.RecycledViewPool = categoryAdapter.getViewPool()


}