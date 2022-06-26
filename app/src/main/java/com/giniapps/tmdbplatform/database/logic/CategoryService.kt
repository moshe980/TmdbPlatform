package com.giniapps.tmdbplatform.database.logic

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giniapps.tmdbplatform.model.response.Category
import com.giniapps.tmdbplatform.model.response.CategoryWithMovies
import com.giniapps.tmdbplatform.model.response.CategoryWithMoviesCrossRef
import com.giniapps.tmdbplatform.model.response.Genre

interface CategoryService {
    suspend fun getAllCategories(): List<CategoryWithMovies>
    suspend fun getSpecificCategory(id: String): CategoryWithMovies
    suspend fun createCategory(category: Category)
    suspend fun createAllCategories(categories: List<Category>)
    suspend fun deleteCategory(category: Category)
    suspend fun createCategoryWithMoviesCrossRef(categoryWithMoviesCrossRef: CategoryWithMoviesCrossRef)
}