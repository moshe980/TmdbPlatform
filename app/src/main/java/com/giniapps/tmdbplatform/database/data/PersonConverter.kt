package com.giniapps.tmdbplatform.database.data

import com.giniapps.tmdbplatform.model.response.Person

class PersonConverter {
    fun convertToBoundary(personEntity: PersonEntity): Person {
        return Person(
            personEntity.id,
            personEntity.birthDay,
            personEntity.deathDay,
            personEntity.biography,
            personEntity.placeOfBirth,
            personEntity.alsoKnownAs
        )

    }

    fun convertToEntity(person: Person): PersonEntity {
        return PersonEntity(
            person.id,
            person.birthDay ?: "---",
            person.deathDay ?: "---",
            person.biography,
            person.placeOfBirth ?: "---",
            person.alsoKnownAs ?: emptyList()
        )


    }
}