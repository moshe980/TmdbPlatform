package com.giniapps.tmdbplatform.database.logic

import androidx.room.*
import com.giniapps.tmdbplatform.model.response.Person
import com.giniapps.tmdbplatform.model.response.TmdbItem


interface PersonService {
    fun getAllPersons(): List<Person>
    fun getSpecificPerson(id: String): Person
    fun createPerson(person: Person)
    fun deletePerson(person: Person)
}