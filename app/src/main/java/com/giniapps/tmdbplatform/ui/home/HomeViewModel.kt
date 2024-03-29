package com.giniapps.tmdbplatform.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.giniapps.tmdbplatform.MediaCategoryEnum
import com.giniapps.tmdbplatform.repository.TmdbRepositoryLogic
import com.giniapps.tmdbplatform.model.response.MediaCategory
import com.giniapps.tmdbplatform.repository.TmdbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    @Inject
    lateinit var categoryAdapter: CategoryAdapter

    @Inject
    lateinit var repository: TmdbRepository

    fun getMovies() {
        viewModelScope.launch {
            val listCategory = listOf(
                MediaCategory(
                    UUID.randomUUID(),
                    MediaCategoryEnum.POPULAR_MOVIES.value,
                    repository.getMoviesByCategory("0").movies
                ),
                MediaCategory(
                    UUID.randomUUID(),
                    MediaCategoryEnum.LATEST_MOVIES.value,
                    repository.getMoviesByCategory("1").movies
                ),
                MediaCategory(
                    UUID.randomUUID(),
                    MediaCategoryEnum.TOP_RATED_MOVIES.value,
                    repository.getMoviesByCategory("2").movies
                ),
                MediaCategory(
                    UUID.randomUUID(),
                    MediaCategoryEnum.POPULAR_TV_SHOWS.value,
                    repository.getMoviesByCategory("3").movies
                ),
                MediaCategory(
                    UUID.randomUUID(),
                    MediaCategoryEnum.LATEST_TV_SHOWS.value,
                    repository.getMoviesByCategory("4").movies
                ),
                MediaCategory(
                    UUID.randomUUID(),
                    MediaCategoryEnum.TOP_RATED_TV_SHOWS.value,
                    repository.getMoviesByCategory("5").movies
                ),
            )
            categoryAdapter.setData(listCategory)
        }

    }


    fun getAdapter(): CategoryAdapter = categoryAdapter


}