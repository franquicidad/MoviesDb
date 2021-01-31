package com.franco.moviesdb.repository.movieActionRepository

import android.accounts.NetworkErrorException
import android.util.Log
import com.franco.moviesdb.database.localDatasources.movies.localdatasourceMovieAction.LocalDatasourceMoviesActionImpl
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.remoteDatasourceMovieAction.RemoteDatasourceMovieActionImpl
import com.franco.moviesdb.util.NetworkUtils
import kotlinx.coroutines.flow.Flow
import java.net.ConnectException

class MovieActionRepositoryImpl(
        private val localDatasourceMoviesAction: LocalDatasourceMoviesActionImpl,
        private val remoteDatasourceMoviesAction: RemoteDatasourceMovieActionImpl,
        private val isInternetAvailable: NetworkUtils

) : MovieActionRepository {
    companion object {
        const val PAGE_SIZE = 5
        const val PAGE_THRESHOLD = 10
    }
    override fun getMovieListActionByQuery(query: String): Flow<List<MovieActionDomain>> =
            localDatasourceMoviesAction.getListBySearchBarMovieActionFromDatabase(query)

    override suspend fun checkRequireNewPageMovieAction(lastVisible: Int) {
        if (isInternetAvailable.isInternetAvailable()) {
            val size = localDatasourceMoviesAction.movieActionSize()
            if (lastVisible >= size - MovieActionRepositoryImpl.PAGE_THRESHOLD) {
                val page = size / MovieActionRepositoryImpl.PAGE_SIZE + 1
                try {
                    val newMovies = remoteDatasourceMoviesAction.getMovieListAction(page)
                    localDatasourceMoviesAction.saveMovieActionToDb(newMovies)
                } catch (connectMovieAction: ConnectException) {
                    Log.i("Net", "NetworkException: $connectMovieAction")
                } catch (network: NetworkErrorException) {
                }

            }
        }
    }
}