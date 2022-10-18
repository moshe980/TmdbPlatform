package com.giniapps.tmdbplatform.database.logic

import androidx.room.Transaction
import com.giniapps.tmdbplatform.model.response.Genre

interface TmdbDataSource :MediaService,PersonService,GenreService,WatchListService,CategoryService {


}