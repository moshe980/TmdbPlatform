package com.giniapps.tmdbplatform.database.dal

import androidx.room.*
import com.giniapps.tmdbplatform.model.response.WatchListItem

@Dao
interface WatchListDao {
    @Query("SELECT * FROM WATCH_LIST")
    suspend  fun findAllItems(): List<WatchListItem>

    @Transaction
    @Query("SELECT * FROM WATCH_LIST WHERE watchListId=:id")
    suspend  fun findItemById(id: String): WatchListItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun saveItem(item: WatchListItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun saveAllItems(items: List<WatchListItem>)

    @Delete()
    suspend fun deleteItem(item: WatchListItem)



}