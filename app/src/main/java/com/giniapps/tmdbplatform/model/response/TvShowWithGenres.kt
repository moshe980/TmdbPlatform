package com.giniapps.tmdbplatform.model.response

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TvShowWithGenres(
    @Embedded
    val tvShow: Media,

    @Relation(
        parentColumn = "id",
        entityColumn = "genreId",
        associateBy = Junction(TvShowWithGenreCrossRef::class)
    )
    val genres: List<Genre>
){

}
