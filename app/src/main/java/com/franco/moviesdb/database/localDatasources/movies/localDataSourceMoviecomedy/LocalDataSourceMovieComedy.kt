package com.franco.moviesdb.database.localDatasources.movies.localDataSourceMoviecomedy

import com.franco.moviesdb.domain.MovieActionDomain
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceMovieComedy {
    suspend fun movieComedySize(): Int
    suspend fun saveMovieComedyToDb(movie: List<MovieActionDomain>)
    fun getAllMoviesComedyFromDatabase(): Flow<List<MovieActionDomain>>
    fun getListBySearchBarMovieComedyFromDatabase(query: String): Flow<List<MovieActionDomain>>

}