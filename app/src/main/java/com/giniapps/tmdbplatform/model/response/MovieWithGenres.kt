package com.giniapps.tmdbplatform.model.response

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithGenres(
    @Embedded
    val movie: Movie,

    @Relation(
        parentColumn = "id",
        entityColumn = "genreId",
        associateBy = Junction(MovieWithGenreCrossRef::class)
    )
    val genres: List<Genre>
){

}
