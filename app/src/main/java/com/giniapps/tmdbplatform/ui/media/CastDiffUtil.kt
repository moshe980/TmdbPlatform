package com.giniapps.tmdbplatform.ui.media

import androidx.recyclerview.widget.DiffUtil
import com.giniapps.tmdbplatform.model.response.Cast
import com.giniapps.tmdbplatform.model.response.TmdbItem
import javax.inject.Inject

class CastDiffUtil @Inject constructor(
    private val oldList: List<Cast>,
    private val newList: List<Cast>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].creditId == newList[newItemPosition].creditId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].creditId != newList[newItemPosition].creditId -> {
                false
            }
            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }
            oldList[oldItemPosition].credit != newList[newItemPosition].credit -> {
                false
            }
            oldList[oldItemPosition].profilePath != newList[newItemPosition].profilePath -> {
                false
            }

            else -> true


        }
    }
}