package com.giniapps.tmdbplatform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.giniapps.tmdbplatform.model.response.TmdbItem


class SharedViewmodel : ViewModel() {
    private var _favoriteList = MutableLiveData<MutableList<TmdbItem>>()
    val favoriteList: LiveData<MutableList<TmdbItem>> get() = _favoriteList
    init {
        _favoriteList.value= mutableListOf<TmdbItem>()
    }

    fun addFavorite(newMedia: TmdbItem) {
        _favoriteList.value?.add(newMedia)
    }

    fun removeFavorite(newMedia: TmdbItem) {
        _favoriteList.value?.remove(newMedia)
    }
}