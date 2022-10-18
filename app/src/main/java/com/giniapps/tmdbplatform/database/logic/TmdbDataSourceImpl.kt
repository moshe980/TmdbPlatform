package com.giniapps.tmdbplatform.database.logic

import com.giniapps.tmdbplatform.database.dal.*
import com.giniapps.tmdbplatform.model.response.*

class TmdbDataSourceImpl(
    private val movieDao: MediaDao,
    private val personDao: PersonDao,
    private val genreDao: GenreDao,
    private val watchListDao: WatchListDao,
    private val categoryDao: CategoryDao,

    ) : TmdbDataSource {

    //Movies:
    override suspend fun getAllMovies(): List<Media> = movieDao.findAllMovies()
    override suspend fun getSpecificMovie(id: String): MovieWithGenres =
        movieDao.findMovieWithGenresById(id)

    override suspend fun createMovie(movie: Media) = movieDao.saveMovie(movie)
    override suspend fun createAllMovies(movies: List<Media>) = movieDao.saveAllMovies(movies)
    override suspend fun deleteMovie(movie: Media) = movieDao.deleteMovie(movie)
    override suspend fun getMoviesWithGenres(): List<MovieWithGenres> = movieDao.findAllMoviesWithGenres()
    override suspend fun getAllLatestMovies(): List<Media> = movieDao.findAllMoviesByLatest()
    override suspend fun getAllPopularMovies(): List<Media> = movieDao.findAllMoviesByPopularity()
    override suspend fun createMovieGenreCrossRef(movieWithGenreCrossRef: MovieWithGenreCrossRef) =
        movieDao.addMovieGenreCrossRef(movieWithGenreCrossRef)

    override suspend fun getAllMoviesByName(movieName: String): List<MovieWithGenres> =
        movieDao.findAllMoviesByName(movieName)

    //Persons:
    override suspend fun getAllPersons(): List<Person> = personDao.findAllPersons()
    override suspend fun getSpecificPerson(id: String): Person = personDao.findPersonById(id)
    override suspend fun createPerson(person: Person) = personDao.savePerson(person)
    override suspend fun deletePerson(person: Person) = personDao.deletePerson(person)

    //Genres:
    override suspend fun getAllGenres(): List<Genre> = genreDao.findAllGenres()
    override suspend fun getSpecificGenre(id: String): Genre = genreDao.findGenreById(id)
    override suspend fun createGenre(genre: Genre) = genreDao.saveGenre(genre)
    override suspend fun createAllGenres(genres: List<Genre>) = genreDao.saveAllGenre(genres)
    override suspend fun deleteGenre(genre: Genre) = genreDao.deleteGenre(genre)

    //WatchList:
    override suspend fun getAllItems(): List<WatchListItem> = watchListDao.findAllItems()

    override suspend fun getSpecificItem(id: String): WatchListItem = watchListDao.findItemById(id)

    override suspend fun createItem(item: WatchListItem) = watchListDao.saveItem(item)

    override suspend fun createAllItems(items: List<WatchListItem>) = watchListDao.saveAllItems(items)

    override suspend fun deleteItem(item: WatchListItem) = watchListDao.deleteItem(item)

    //Category
    override suspend fun getAllCategories(): List<CategoryWithMovies> =
        categoryDao.findAllCategories()

    override suspend fun getSpecificCategory(id: String): CategoryWithMovies =
        categoryDao.findCategoryById(id)

    override suspend fun createCategory(category: Category) = categoryDao.saveCategory(category)

    override suspend fun createAllCategories(categories: List<Category>) =
        categoryDao.saveAllCategories(categories)

    override suspend fun deleteCategory(category: Category) = categoryDao.deleteCategory(category)
    override suspend fun createCategoryWithMoviesCrossRef(categoryWithMoviesCrossRef: CategoryWithMoviesCrossRef) =
        categoryDao.addCategoryWithMoviesCrossRef(categoryWithMoviesCrossRef)


}