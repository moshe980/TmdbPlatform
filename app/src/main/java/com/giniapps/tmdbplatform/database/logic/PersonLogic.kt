package com.giniapps.tmdbplatform.database.logic

import com.giniapps.tmdbplatform.database.dal.MovieDao
import com.giniapps.tmdbplatform.database.dal.PersonDao
import com.giniapps.tmdbplatform.database.dal.TvShowDao
import com.giniapps.tmdbplatform.database.data.PersonConverter
import com.giniapps.tmdbplatform.model.response.Person

class PersonLogic(private val personDao: PersonDao) :
    PersonService {
    private val converter = PersonConverter()
    override fun getAllPersons(): List<Person> {
        return personDao.findAllPersons().map { converter.convertToBoundary(it) }
    }

    override fun getSpecificPerson(id: String): Person {
        return converter.convertToBoundary(personDao.findPersonById(id))
    }

    override fun createPerson(person: Person) {
        val personEntity = converter.convertToEntity(person)
        personDao.savePerson(personEntity)

    }

    override fun deletePerson(person: Person) {
        personDao.deletePerson(converter.convertToEntity(person))
    }
}