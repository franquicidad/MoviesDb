package com.franco.moviesdb.database.localDatasources.movies.localDatasourceTvComedy

import com.franco.moviesdb.domain.MovieActionDomain
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceTvComedy {
    suspend fun tvComedySize(): Int
    suspend fun saveTvComedyToDb(tv: List<MovieActionDomain>)
    fun getAllTvSeriesComedyFromDatabase(): Flow<List<MovieActionDomain>>
    fun getListBySearchBarTvComedyFromDatabase(query: String): Flow<List<MovieActionDomain>>
}