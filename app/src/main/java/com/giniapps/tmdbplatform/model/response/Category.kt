package com.giniapps.tmdbplatform.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CATEGORIES")
data class Category(
    @PrimaryKey
    val categoryId : String,
    val name:String
)
