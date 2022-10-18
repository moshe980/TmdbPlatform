package com.giniapps.tmdbplatform.database.logic

import com.giniapps.tmdbplatform.model.response.WatchListItem

interface WatchListService {
    suspend fun getAllItems(): List<WatchListItem>
    suspend fun getSpecificItem(id: String): WatchListItem
    suspend fun createItem(item: WatchListItem)
    suspend fun createAllItems(items: List<WatchListItem>)
    suspend fun deleteItem(item: WatchListItem)

}