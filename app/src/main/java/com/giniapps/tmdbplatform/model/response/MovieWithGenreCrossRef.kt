package com.giniapps.tmdbplatform.model.response

import androidx.room.Entity

@Entity(primaryKeys = ["id","genreId"])
data class MovieWithGenreCrossRef (
    val id:Long,
    val genreId:Long,
)