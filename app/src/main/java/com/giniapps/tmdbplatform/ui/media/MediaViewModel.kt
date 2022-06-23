package com.giniapps.tmdbplatform.ui.media

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giniapps.tmdbplatform.model.response.Failure
import com.giniapps.tmdbplatform.model.response.Success
import com.giniapps.tmdbplatform.model.response.TmdbItem
import com.giniapps.tmdbplatform.model.response.VideoWrapper
import com.giniapps.tmdbplatform.networking.RemoteApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var remoteApi: RemoteApi

    @Inject
    lateinit var adapter: CastsAdapter


    private val _video = MutableLiveData<VideoWrapper>()
    val video: LiveData<VideoWrapper> get() = _video
    private var mediaItem: TmdbItem? = null
    private val _hasServerConnection = MutableLiveData<String>()
    val hasServerConnection: LiveData<String> get() = _hasServerConnection


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

    fun getCredits(mediaItem: TmdbItem) {
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


}