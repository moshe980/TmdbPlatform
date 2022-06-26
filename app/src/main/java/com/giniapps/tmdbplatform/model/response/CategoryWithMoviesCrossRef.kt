package com.giniapps.tmdbplatform.model.response

import androidx.room.Entity

@Entity(primaryKeys = ["categoryId", "id"])
data class CategoryWithMoviesCrossRef (
    val categoryId: String,
    val id: Long,
)
