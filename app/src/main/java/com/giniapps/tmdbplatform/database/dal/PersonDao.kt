package com.giniapps.tmdbplatform.database.dal

import androidx.room.*
import com.giniapps.tmdbplatform.database.data.PersonEntity
import com.giniapps.tmdbplatform.model.response.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM PERSONS")
    fun findAllPersons(): List<PersonEntity>

    @Transaction
    @Query("SELECT * FROM PERSONS WHERE id=:id")
    fun findPersonById(id: String): PersonEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePerson(person: PersonEntity)

    @Delete()
    fun deletePerson(person: PersonEntity)

}
