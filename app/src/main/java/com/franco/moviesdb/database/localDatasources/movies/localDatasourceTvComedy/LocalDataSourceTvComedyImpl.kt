package com.franco.moviesdb.database.localDatasources.movies.localDatasourceTvComedy

import com.franco.moviesdb.database.MovieDatabase
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.mapFromDomainToTvDatabaseComedy
import com.franco.moviesdb.network.mapTvDatabaseToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceTvComedyImpl @Inject constructor(
        private val movieDb: MovieDatabase,

        ) : LocalDataSourceTvComedy {
    override suspend fun tvComedySize(): Int {
        return movieDb.tvComedyDAO().tvCountComedy()

    }

    override suspend fun saveTvComedyToDb(tv: List<MovieActionDomain>) {
        movieDb.tvComedyDAO().insertTvComedy(tv.map {
            it.mapFromDomainToTvDatabaseComedy()
        })
    }

    override fun getAllTvSeriesComedyFromDatabase(): Flow<List<MovieActionDomain>> =
            movieDb.tvActionDAO().getAllTvActionByGenre().map { tv ->
                tv.map {
                    it.mapTvDatabaseToDomain()
                }

            }

    override fun getListBySearchBarTvComedyFromDatabase(query: String): Flow<List<MovieActionDomain>> =
            movieDb.tvComedyDAO().getListBySearchBarTvComedy(query).map { tvFiltered ->
                tvFiltered.map {
                    it.mapTvDatabaseToDomain()
                }
            }
}