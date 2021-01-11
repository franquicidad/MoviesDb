package com.franco.moviesdb.repository.tvComedyRepository

import com.franco.moviesdb.database.localDatasources.movies.localDatasourceTvComedy.LocalDataSourceTvComedyImpl
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.remoteDatasourceTvComedy.RemoteDataSourceTvComedyImpl
import kotlinx.coroutines.flow.Flow

class TvComedyRepositoryImpl(
        private val localDatasourceTvComedy: LocalDataSourceTvComedyImpl,
        private val remoteDatasourceTvComedy: RemoteDataSourceTvComedyImpl,
) : TvComedyRepository {
    override fun getTvListComedyByQuery(query: String): Flow<List<MovieActionDomain>> {
        localDatasourceTvComedy.getListBySearchBarTvComedyFromDatabase(query)

    }

    override suspend fun checkRequireNewPageTvComedy(lastVisible: Int) {
        val size = localDatasourceTvComedy.tvComedySize()
        if (lastVisible >= size - RepositoryImpl.PAGE_THRESHOLD) {
            val page = size / RepositoryImpl.PAGE_SIZE + 1
            val tvCom = remoteDatasourceTvComedy.getTvListComedy(page)
            localDatasourceTvComedy.saveTvComedyToDb(tvCom)
        }
    }
}