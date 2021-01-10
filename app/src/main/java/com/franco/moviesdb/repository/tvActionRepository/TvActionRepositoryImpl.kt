package com.franco.moviesdb.repository.tvActionRepository


import com.franco.moviesdb.database.localDatasources.movies.localDatasourceTvAction.LocalDataSourceTvActionImpl
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.domain.RepositoryImpl
import com.franco.moviesdb.network.remoteDatasourceTvAction.RemoteDatasourceTvActionImpl
import com.franco.moviesdb.repository.movieComedyRepository.MovieComedyRepositoryImpl.Companion.PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class TvActionRepositoryImpl(
        private val localDatasourceTvAction: LocalDataSourceTvActionImpl,
        private val remoteDatasourceMovieComedy: RemoteDatasourceTvActionImpl
) : TvActionRepository {

    companion object {

        const val PAGE_SIZE = 5
        const val PAGE_THRESHOLD = 10

    }

    override fun getTvListActionByQuery(query: String): Flow<List<MovieActionDomain>> =
            localDatasourceTvAction.getListBySearchBarTvActionFromDatabase(query)


    override suspend fun checkRequireNewPageTvAction(lastVisible: Int) {
        val size = localDatasourceTvAction.tvActionSize()

        if (lastVisible >= size - TvActionRepositoryImpl.PAGE_THRESHOLD) {
            val page = size / TvActionRepositoryImpl.PAGE_SIZE + 1
            val newMovies = remoteDatasourceMovieComedy.getTvListAction(page)
            localDatasourceTvAction.saveTvActionToDb(newMovies)
        }
    }
}