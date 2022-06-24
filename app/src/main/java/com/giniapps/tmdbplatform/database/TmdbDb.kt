package com.giniapps.tmdbplatform.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.giniapps.tmdbplatform.database.dal.MovieDao
import com.giniapps.tmdbplatform.database.dal.PersonDao
import com.giniapps.tmdbplatform.database.dal.TvShowDao
import com.giniapps.tmdbplatform.database.data.ListToStringConverter
import com.giniapps.tmdbplatform.database.data.MovieEntity
import com.giniapps.tmdbplatform.database.data.PersonEntity
import com.giniapps.tmdbplatform.database.data.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class, PersonEntity::class], version = 1)
@TypeConverters(ListToStringConverter::class)
abstract class TmdbDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun personDao(): PersonDao

    companion object {
        fun create(context: Context): TmdbDb =
            Room.databaseBuilder(context, TmdbDb::class.java, "TmdbDB")
                .allowMainThreadQueries()//don't use in production
                .fallbackToDestructiveMigration()
                .build()
    }
}