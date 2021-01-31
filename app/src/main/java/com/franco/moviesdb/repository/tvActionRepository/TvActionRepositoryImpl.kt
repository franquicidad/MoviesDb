package com.franco.moviesdb.repository.tvActionRepository


import android.accounts.NetworkErrorException
import android.util.Log
import com.franco.moviesdb.database.localDatasources.movies.localDatasourceTvAction.LocalDataSourceTvActionImpl
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.remoteDatasourceTvAction.RemoteDatasourceTvActionImpl
import com.franco.moviesdb.util.NetworkUtils
import kotlinx.coroutines.flow.Flow
import java.net.ConnectException

class TvActionRepositoryImpl(
        private val localDatasourceTvAction: LocalDataSourceTvActionImpl,
        private val remoteDatasourceMovieComedy: RemoteDatasourceTvActionImpl,
        private val isInternetAvailable: NetworkUtils

) : TvActionRepository {

    companion object {

        const val PAGE_SIZE = 5
        const val PAGE_THRESHOLD = 10

    }

    override fun getTvListActionByQuery(query: String): Flow<List<MovieActionDomain>> =
            localDatasourceTvAction.getListBySearchBarTvActionFromDatabase(query)


    override suspend fun checkRequireNewPageTvAction(lastVisible: Int) {
        if (isInternetAvailable.isInternetAvailable()) {
            val size = localDatasourceTvAction.tvActionSize()

            if (lastVisible >= size - TvActionRepositoryImpl.PAGE_THRESHOLD) {
                val page = size / TvActionRepositoryImpl.PAGE_SIZE + 1
                try {
                    val newMovies = remoteDatasourceMovieComedy.getTvListAction(page)
                    localDatasourceTvAction.saveTvActionToDb(newMovies)
                } catch (connect: ConnectException) {
                    Log.i("Net", "NetworkException: $connect")
                } catch (network: NetworkErrorException) {
                }
            }
        }
    }
}