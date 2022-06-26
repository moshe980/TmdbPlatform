package com.giniapps.tmdbplatform.ui.searchMedia

import androidx.recyclerview.widget.DiffUtil
import com.giniapps.tmdbplatform.model.response.Movie
import com.giniapps.tmdbplatform.model.response.MovieWithGenres
import com.giniapps.tmdbplatform.model.response.TmdbItem
import javax.inject.Inject

class MyDiffUtil @Inject constructor(private val oldList: List<TmdbItem>, private val newList: List<TmdbItem>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }
            oldList[oldItemPosition].backdropPath != newList[newItemPosition].backdropPath -> {
                false
            }
            oldList[oldItemPosition].overview != newList[newItemPosition].overview -> {
                false
            }
            oldList[oldItemPosition].releaseDate != newList[newItemPosition].releaseDate -> {
                false
            }
            oldList[oldItemPosition].voteAverage != newList[newItemPosition].voteAverage -> {
                false
            }
            oldList[oldItemPosition].posterPath != newList[newItemPosition].posterPath -> {
                false
            }

            else -> true


        }
    }
}