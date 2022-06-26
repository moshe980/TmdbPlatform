package com.giniapps.tmdbplatform.database.dal

import androidx.room.*
import com.giniapps.tmdbplatform.model.response.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM PERSONS")
    suspend fun findAllPersons(): List<Person>

    @Transaction
    @Query("SELECT * FROM PERSONS WHERE id=:id")
    suspend  fun findPersonById(id: String): Person

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePerson(person: Person)

    @Delete()
    suspend fun deletePerson(person: Person)

}
