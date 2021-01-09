package com.franco.moviesdb.database

import com.franco.moviesdb.database.actors.model.ResponceWithActor
import com.franco.moviesdb.domain.MovieActionDomain
import kotlinx.coroutines.flow.Flow

interface LocalDatasource {
    suspend fun movieActionSize(): Int
    suspend fun movieComedySize(): Int
    suspend fun tvActionSize(): Int
    suspend fun tvComedySize(): Int
    suspend fun saveMovieActionToDb(movie: List<MovieActionDomain>)
    suspend fun saveMovieComedyToDb(movie: List<MovieActionDomain>)
    suspend fun saveTvActionToDb(tv: List<MovieActionDomain>)
    suspend fun saveTvComedyToDb(tv: List<MovieActionDomain>)
    fun getAllMoviesActionFromDatabase(): Flow<List<MovieActionDomain>>
    fun getAllMoviesComedyFromDatabase(): Flow<List<MovieActionDomain>>
    fun getAllTvSeriesActionFromDatabase(): Flow<List<MovieActionDomain>>
    fun getAllTvSeriesComedyFromDatabase(): Flow<List<MovieActionDomain>>
    fun getListBySearchBarMovieActionFromDatabase(query: String): Flow<List<MovieActionDomain>>
    fun getListBySearchBarMovieComedyFromDatabase(query: String): Flow<List<MovieActionDomain>>
    fun getListBySearchBarTvActionFromDatabase(query: String): Flow<List<MovieActionDomain>>
    fun getListBySearchBarTvComedyFromDatabase(query: String): Flow<List<MovieActionDomain>>
}