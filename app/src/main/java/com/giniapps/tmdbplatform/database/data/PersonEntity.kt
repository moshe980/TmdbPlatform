package com.giniapps.tmdbplatform.database.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "PERSONS")
data class PersonEntity(
    @PrimaryKey
    val id: Int,
    val birthDay: String,
    val deathDay: String,
    val biography: String,
    val placeOfBirth: String,
    val alsoKnownAs: List<String>,
)
