package com.giniapps.tmdbplatform.ui.searchMedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giniapps.tmdbplatform.model.response.Failure
import com.giniapps.tmdbplatform.model.response.Success
import com.giniapps.tmdbplatform.model.response.TmdbItem
import com.giniapps.tmdbplatform.networking.RemoteApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMediaViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var remoteApi: RemoteApi

    @Inject
    lateinit var mediaInfoAdapter: MediaInfoAdapter

    private val _hasServerConnection = MutableLiveData<String>()
    val hasServerConnection: LiveData<String> get() = _hasServerConnection


    fun getAdapter(): MediaInfoAdapter = mediaInfoAdapter

    fun getMoviesFromApi(movieName: String) {
        viewModelScope.launch {
            val result = remoteApi.searchMovies(1, movieName)
            if (result is Success) {
                val list = result.data.items
                list.sortedBy { it.name  }
                mediaInfoAdapter.setData(list)
            } else if (result is Failure) {
                _hasServerConnection.value = result.exc?.message
                _hasServerConnection.postValue(_hasServerConnection.value)

            }

        }
    }

    fun getSeriesFromApi(seriesName: String) {
        viewModelScope.launch {
            val result = remoteApi.searchTVShows(1, seriesName)
            if (result is Success) {
                val list = result.data.items
                list.sortedBy { it.name  }
                mediaInfoAdapter.setData(list)
            } else if (result is Failure) {
                _hasServerConnection.value = result.exc?.message
                _hasServerConnection.postValue(_hasServerConnection.value)

            }

        }
    }

}