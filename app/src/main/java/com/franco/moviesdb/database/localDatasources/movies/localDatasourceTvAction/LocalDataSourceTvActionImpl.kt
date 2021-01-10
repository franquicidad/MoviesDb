package com.franco.moviesdb.database.localDatasources.movies.localDatasourceTvAction

import com.franco.moviesdb.database.MovieDatabase
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.mapFromDomainToTvDatabase
import com.franco.moviesdb.network.mapTvDatabaseToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceTvActionImpl @Inject constructor(
        private val movieDb: MovieDatabase,

        ) : LocalDataSourceTvAction {

    override suspend fun tvActionSize(): Int {
        return movieDb.tvActionDAO().tvCountAction()

    }

    override suspend fun saveTvActionToDb(tv: List<MovieActionDomain>) {
        movieDb.tvActionDAO().insertTvAction(tv.map {
            it.mapFromDomainToTvDatabase()
        })
    }

    override fun getAllTvSeriesActionFromDatabase(): Flow<List<MovieActionDomain>> =
            movieDb.tvActionDAO().getAllTvActionByGenre().map { tv ->
                tv.map {
                    it.mapTvDatabaseToDomain()
                }
            }

    override fun getListBySearchBarTvActionFromDatabase(query: String): Flow<List<MovieActionDomain>> =
            movieDb.tvActionDAO().getListBySearchBarTvAction(query).map { tvFiltered ->
                tvFiltered.map {
                    it.mapTvDatabaseToDomain()
                }
            }
}