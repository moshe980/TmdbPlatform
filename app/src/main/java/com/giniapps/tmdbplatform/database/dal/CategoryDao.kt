package com.giniapps.tmdbplatform.database.dal

import androidx.room.*
import com.giniapps.tmdbplatform.model.response.*

@Dao
interface CategoryDao {
    @Query("SELECT * FROM CATEGORIES")
    suspend fun findAllCategories(): List<CategoryWithMovies>

    @Query("SELECT * FROM CATEGORIES WHERE categoryId=:id")
    suspend fun findCategoryById(id: String): CategoryWithMovies

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun saveCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllCategories(genres: List<Category>)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategoryWithMoviesCrossRef(categoryWithMoviesCrossRef: CategoryWithMoviesCrossRef)
}