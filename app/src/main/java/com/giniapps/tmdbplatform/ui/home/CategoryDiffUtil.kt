package com.giniapps.tmdbplatform.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.giniapps.tmdbplatform.model.response.MediaCategory
import javax.inject.Inject

class CategoryDiffUtil @Inject constructor(private val oldList: List<MediaCategory>, private val newList: List<MediaCategory>) :
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
            oldList[oldItemPosition].list != newList[newItemPosition].list -> {
                false
            }
            else -> true


        }
    }
}