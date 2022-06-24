package com.giniapps.tmdbplatform.database.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "TV_SHOWS")
data class TvShowEntity(
    @PrimaryKey
     val id: Int,
      val overview: String,
      val releaseDate: String,
      val posterPath: String,
      val backdropPath: String,
      val name: String,
      val voteAverage: Double
)
