package com.giniapps.tmdbplatform.di

import android.content.Context
import androidx.room.Room
import com.giniapps.tmdbplatform.AppManager
import com.giniapps.tmdbplatform.database.TmdbDb
import com.giniapps.tmdbplatform.database.logic.TmdbDataSource
import com.giniapps.tmdbplatform.database.logic.TmdbDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class TmdbDataResourceModule {
    private val DB_NAME = "TmdbDB"

    @Provides
    fun provideTmdbDataResource(@ApplicationContext context: Context): TmdbDataSource {
        val db = Room.databaseBuilder(context, TmdbDb::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

        return TmdbDataSourceImpl(db.movieDao(), db.personDao(), db.genreDao(),db.watchListDao(),db.categoryDao())
    }
}