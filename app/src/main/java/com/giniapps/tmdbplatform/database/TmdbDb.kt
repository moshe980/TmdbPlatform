package com.giniapps.tmdbplatform.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.giniapps.tmdbplatform.database.dal.*
import com.giniapps.tmdbplatform.model.response.*

@Database(
    entities = [
        Movie::class,
        TVShow::class,
        Person::class,
        MovieWithGenreCrossRef::class,
        Genre::class,
        WatchListItem::class,
        TvShowWithGenreCrossRef::class,
        Category::class,
        CategoryWithMoviesCrossRef::class,

    ],
    version = 2
)
@TypeConverters(SetToStringConverter::class, ListToStringConverter::class)
abstract class TmdbDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun personDao(): PersonDao
    abstract fun genreDao(): GenreDao
    abstract fun watchListDao(): WatchListDao
    abstract fun categoryDao(): CategoryDao
}