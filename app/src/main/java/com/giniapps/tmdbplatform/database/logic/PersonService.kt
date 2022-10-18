package com.giniapps.tmdbplatform.database.logic

import androidx.room.*
import com.giniapps.tmdbplatform.model.response.Person


interface PersonService {
    suspend fun getAllPersons(): List<Person>
    suspend fun getSpecificPerson(id: String): Person
    suspend fun createPerson(person: Person)
    suspend fun deletePerson(person: Person)
}