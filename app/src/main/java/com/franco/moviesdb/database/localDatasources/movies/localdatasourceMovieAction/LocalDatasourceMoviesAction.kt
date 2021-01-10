package com.franco.moviesdb.database.localDatasources.movies.localdatasourceMovieAction

import com.franco.moviesdb.domain.MovieActionDomain
import kotlinx.coroutines.flow.Flow

interface LocalDatasourceMoviesAction {
    suspend fun movieActionSize(): Int
    suspend fun saveMovieActionToDb(movie: List<MovieActionDomain>)
    fun getAllMoviesActionFromDatabase(): Flow<List<MovieActionDomain>>
    fun getListBySearchBarMovieActionFromDatabase(query: String): Flow<List<MovieActionDomain>>

}