package com.giniapps.tmdbplatform.ui.searchMedia

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.giniapps.tmdbplatform.model.response.Media
import com.giniapps.tmdbplatform.networking.RemoteApi
import com.giniapps.tmdbplatform.repository.TmdbRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchMediaViewModel @Inject constructor(
    state: SavedStateHandle,
) : ViewModel() {

    @Inject
    lateinit var repository: TmdbRepository

    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = "~~"
    }

    val movies = currentQuery.switchMap {
        repository.getSearchResults(it).cachedIn(viewModelScope)
    }

    fun searchMovies(query: String) {
        currentQuery.value = query
    }

    fun getSeries(seriesName: String) {
        //   mediaInfoAdapter.setData(repository.findAllMoviesByName(seriesName).map { it.movie })
        //  mediaInfoAdapter.retry()
    }


}