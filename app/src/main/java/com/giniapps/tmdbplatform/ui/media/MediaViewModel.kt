package com.giniapps.tmdbplatform.ui.media

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giniapps.tmdbplatform.model.response.Media
import com.giniapps.tmdbplatform.model.response.MovieWithGenres
import com.giniapps.tmdbplatform.model.response.VideoWrapper
import com.giniapps.tmdbplatform.model.response.WatchListItem
import com.giniapps.tmdbplatform.repository.TmdbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var adapter: CastsAdapter

    @Inject
    lateinit var repository: TmdbRepository

    private var _movieWithGenres = MutableLiveData<MovieWithGenres>()
    val movieWithGenres: LiveData<MovieWithGenres> get() = _movieWithGenres

    private var _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    private val _video = MutableLiveData<VideoWrapper>()
    val video: LiveData<VideoWrapper> get() = _video
    private var mediaItem: Media? = null
    private val _hasServerConnection = MutableLiveData<String>()


    fun getVideo(mediaItem: Media) {
        viewModelScope.launch {
            _video.postValue(repository.getTrailerById(mediaItem.id))
        }
    }

    fun getCastsAdapter(): CastsAdapter = adapter

    fun getMovieWithGenres(id: Long) {
        viewModelScope.launch {
            val currentMovieWithGenres = repository.getMovieWithGenreById("$id")
            _movieWithGenres.postValue(currentMovieWithGenres)
        }
    }


    fun getCasts(mediaItem: Media) {
        viewModelScope.launch {
            adapter.setData(repository.getCasts(mediaItem))


        }
    }

    fun setMedia(newMediaItem: Media) {
        mediaItem = newMediaItem
    }

    fun addFavorite(currentMedia: Media) {
        viewModelScope.launch {
            repository.createItemWatchList(WatchListItem(currentMedia.id, currentMedia))

        }
    }

    fun isFavorite(currentMedia: Media) {
        viewModelScope.launch {
            val d=repository.getSpecificItem(currentMedia)?.watchListId!==null
            _isFavorite.postValue(d)

        }

    }

    fun removeFavorite(currentMedia: Media) {
        viewModelScope.launch {
            repository.removeItemWatchList(WatchListItem(currentMedia.id, currentMedia))

        }

    }


}