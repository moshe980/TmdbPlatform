package com.giniapps.tmdbplatform.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val genreId: Long,
    val name: String
)