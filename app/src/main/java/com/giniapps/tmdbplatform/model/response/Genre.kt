package com.giniapps.tmdbplatform.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "GENRES")
data class Genre(
    @PrimaryKey
    @SerializedName("id")
    val genreId: Long,
    val name: String
){

}