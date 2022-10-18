package com.giniapps.tmdbplatform

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SharedViewmodel : ViewModel() {
    private var _favoriteList = MutableLiveData<MutableList<Movie>>()
    val favoriteList: LiveData<MutableList<Movie>> get() = _favoriteList
    init {
        _favoriteList.value= mutableListOf<Movie>()
    }

    fun addFavorite(newMedia: Movie) {
        _favoriteList.value?.add(newMedia)
    }

    fun removeFavorite(newMedia: Movie) {
        _favoriteList.value?.remove(newMedia)
    }
}