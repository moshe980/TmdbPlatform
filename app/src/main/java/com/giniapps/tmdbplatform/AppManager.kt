package com.giniapps.tmdbplatform

import android.app.Application
import com.giniapps.tmdbplatform.database.TmdbDb
import com.giniapps.tmdbplatform.database.logic.PersonLogic
import com.giniapps.tmdbplatform.database.logic.PersonService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppManager : Application() {
    companion object {
        private lateinit var instance: AppManager

        private val db: TmdbDb by lazy {
            TmdbDb.create(instance)
        }

        val personService: PersonService by lazy {
            PersonLogic( db.personDao())
        }


    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}