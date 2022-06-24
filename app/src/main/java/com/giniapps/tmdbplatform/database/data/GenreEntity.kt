package com.giniapps.tmdbplatform.database.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GENRES")
data class GenreEntity(
    @PrimaryKey
     val genreId: Long,
     val name: String
)
