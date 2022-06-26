package com.giniapps.tmdbplatform.ui.searchMedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giniapps.tmdbplatform.repository.TmdbRepositoryLogic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMediaViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var repository: TmdbRepositoryLogic

    @Inject
    lateinit var mediaInfoAdapter: MediaInfoAdapter

    private val _hasServerConnection = MutableLiveData<String>()


    fun getAdapter(): MediaInfoAdapter = mediaInfoAdapter

    fun getMovies(movieName: String) {
        viewModelScope.launch{
            mediaInfoAdapter.setData(repository.findAllMoviesByName(movieName).map { it.movie})

        }
    }

   /* fun getSeries(seriesName: String) {
        mediaInfoAdapter.setData(repository.findAllMoviesByName(seriesName).map { it.movie})
    }*/

}