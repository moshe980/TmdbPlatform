package com.giniapps.tmdbplatform

import android.app.Application
import com.giniapps.tmdbplatform.database.TmdbDb

import com.giniapps.tmdbplatform.database.logic.PersonService
import com.giniapps.tmdbplatform.database.logic.TmdbDataSource
import com.giniapps.tmdbplatform.database.logic.TmdbDataSourceImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppManager : Application()