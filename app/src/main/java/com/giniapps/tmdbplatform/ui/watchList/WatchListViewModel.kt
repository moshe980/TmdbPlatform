package com.giniapps.tmdbplatform.ui.watchList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.giniapps.tmdbplatform.ui.searchMedia.MediaInfoAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var mediaInfoAdapter: MediaInfoAdapter


    fun getAdapter(): MediaInfoAdapter = mediaInfoAdapter
}