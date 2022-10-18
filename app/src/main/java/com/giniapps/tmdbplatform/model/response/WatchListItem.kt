package com.giniapps.tmdbplatform.model.response

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.giniapps.tmdbplatform.database.TmdbDb

@Entity(tableName ="WATCH_LIST")
data class WatchListItem(
    @PrimaryKey
    val watchListId: Long,
    @Embedded
    val item:Media
)
