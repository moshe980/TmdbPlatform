package com.giniapps.tmdbplatform.ui.watchList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giniapps.tmdbplatform.repository.TmdbRepositoryLogic
import com.giniapps.tmdbplatform.ui.searchMedia.MediaInfoAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var mediaInfoAdapter: MediaInfoAdapter

    @Inject
    lateinit var repository: TmdbRepositoryLogic


    fun getAdapter(): MediaInfoAdapter = mediaInfoAdapter
    fun setData() {
        viewModelScope.launch {

            mediaInfoAdapter.setData(repository.getWatchList().map { it.item })

        }
    }


}