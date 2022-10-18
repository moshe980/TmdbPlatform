package com.giniapps.tmdbplatform.ui.searchMedia

import androidx.paging.PagingSource
import com.giniapps.tmdbplatform.model.response.Media
import com.giniapps.tmdbplatform.model.response.Success
import com.giniapps.tmdbplatform.networking.RemoteApi

private const val FIRST_PAGE_INDEX = 1

class MediaPagingSource(private val remoteApi: RemoteApi, val query: String) :
    PagingSource<Int, Media>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val currentPage = params.key ?: FIRST_PAGE_INDEX

        return try {
            val response = remoteApi.searchMovies(currentPage, query)
            val movies: List<Media>? = null
            if (response is Success) {
                val movies = response.data.items
                LoadResult.Page(
                    data = movies,
                    prevKey = if (currentPage == FIRST_PAGE_INDEX) null else currentPage - 1,
                    nextKey = if (movies.isEmpty()) null else currentPage +1
                )

            } else {
                LoadResult.Page(data = movies!!, prevKey = null, nextKey = null)

            }
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }



}