package com.giniapps.tmdbplatform.model.response

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithCategories(
    @Embedded
    val movie: Movie,

    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId",
        associateBy = Junction(CategoryWithMoviesCrossRef::class)
    )
    val genres: List<Category>
){

}
