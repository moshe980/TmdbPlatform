package com.giniapps.tmdbplatform.model.response

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CategoryWithMovies(
    @Embedded
    val category: Category,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id",
        associateBy = Junction(CategoryWithMoviesCrossRef::class)

    )
    val movies: List<Movie>
) {

}
