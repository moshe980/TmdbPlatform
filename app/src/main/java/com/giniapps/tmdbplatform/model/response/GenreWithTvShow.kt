package com.giniapps.tmdbplatform.model.response

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class GenreWithTvShow(
    @Embedded
    val genre: Genre,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "id",
        associateBy = Junction(TvShowWithGenreCrossRef::class)

    )
    val movies: List<TVShow>
)