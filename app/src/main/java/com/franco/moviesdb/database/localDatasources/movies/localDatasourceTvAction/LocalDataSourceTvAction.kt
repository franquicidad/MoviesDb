package com.franco.moviesdb.database.localDatasources.movies.localDatasourceTvAction

import com.franco.moviesdb.domain.MovieActionDomain
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceTvAction {
    suspend fun tvActionSize(): Int
    suspend fun saveTvActionToDb(tv: List<MovieActionDomain>)
    fun getAllTvSeriesActionFromDatabase(): Flow<List<MovieActionDomain>>
    fun getListBySearchBarTvActionFromDatabase(query: String): Flow<List<MovieActionDomain>>

}