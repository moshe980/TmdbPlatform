package com.giniapps.tmdbplatform.ui.media

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giniapps.tmdbplatform.model.response.*
import com.giniapps.tmdbplatform.networking.RemoteApi
import com.giniapps.tmdbplatform.repository.TmdbRepositoryLogic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var remoteApi: RemoteApi//for the videos

    @Inject
    lateinit var adapter: CastsAdapter

    @Inject
    lateinit var repository: TmdbRepositoryLogic

    private var _movieWithGenres = MutableLiveData<MovieWithGenres>()
    val movieWithGenres: LiveData<MovieWithGenres> get() = _movieWithGenres

    private var _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite


    private val _video = MutableLiveData<VideoWrapper>()
    val video: LiveData<VideoWrapper> get() = _video
    private var mediaItem: TmdbItem? = null
    private val _hasServerConnection = MutableLiveData<String>()


    fun getVideo(mediaItem: TmdbItem) {
        viewModelScope.launch {
            val result = remoteApi.movieTrailers(mediaItem.id)
            if (result is Success) {
                _video.postValue(result.data)
            } else if (result is Failure) {
                _hasServerConnection.value = result.exc?.message
                _hasServerConnection.postValue(_hasServerConnection.value)

            }

        }
    }

    fun getCastsAdapter(): CastsAdapter = adapter

    fun getMovieWithGenres(id: Long) {
        viewModelScope.launch {
            val currentMovieWithGenres = repository.getMovieWithGenreById("$id")
            _movieWithGenres.postValue(currentMovieWithGenres)
        }
    }

    /*  fun getTvShowWithGenres(id: Long): TvShowWithGenres {
          repository.getTvShowWithGenreById("$id")
      } */

    fun getCasts(mediaItem: TmdbItem) {
        viewModelScope.launch {
            val result = remoteApi.movieCredits(mediaItem.id)
            if (result is Success) {
                adapter.setData(result.data.cast.take(10))
            } else if (result is Failure) {
                _hasServerConnection.value = result.exc?.message
                _hasServerConnection.postValue(_hasServerConnection.value)

            }

        }
    }

    fun setMedia(newMediaItem: TmdbItem) {
        mediaItem = newMediaItem
    }

    fun addFavorite(currentMedia: TmdbItem) {
        viewModelScope.launch {
            repository.dataSource.createItem(WatchListItem(currentMedia.id, currentMedia as Movie))

        }
    }

    fun isFavorite(currentMedia: TmdbItem) {
        viewModelScope.launch {
            _isFavorite.postValue(repository.dataSource.getSpecificItem("${currentMedia.id}") != null)

        }

    }

    fun removeFavorite(currentMedia: TmdbItem) {
        viewModelScope.launch {
            repository.dataSource.deleteItem(WatchListItem(currentMedia.id, currentMedia as Movie))

        }

    }


}