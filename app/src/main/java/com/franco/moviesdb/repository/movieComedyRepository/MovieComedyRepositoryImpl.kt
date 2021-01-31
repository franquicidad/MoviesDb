package com.franco.moviesdb.repository.movieComedyRepository

import android.accounts.NetworkErrorException
import com.franco.moviesdb.database.localDatasources.movies.localDataSourceMoviecomedy.LocalDataSourceMovieComedyImpl
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.remoteDatasourceMoviecomedy.RemoteDatasourceMovieComedyImpl
import com.franco.moviesdb.util.NetworkUtils

import kotlinx.coroutines.flow.Flow
import java.net.ConnectException

class MovieComedyRepositoryImpl(
        private val localDatasourceMovieComedy: LocalDataSourceMovieComedyImpl,
        private val remoteDatasourceMovieComedy: RemoteDatasourceMovieComedyImpl,
        private val isInternetAvailable: NetworkUtils

) : MovieComedyRepository {

    companion object {

        const val PAGE_SIZE = 5
        const val PAGE_THRESHOLD = 10

    }

    override fun getMovieListComedyByQuery(query: String): Flow<List<MovieActionDomain>> =
            localDatasourceMovieComedy.getListBySearchBarMovieComedyFromDatabase(query)

    override suspend fun checkRequireNewPageMovieComedy(lastVisible: Int) {
        if (isInternetAvailable.isInternetAvailable()) {
            val size = localDatasourceMovieComedy.movieComedySize()
            if (lastVisible >= size - MovieComedyRepositoryImpl.PAGE_THRESHOLD) {
                val page = size / MovieComedyRepositoryImpl.PAGE_SIZE + 1
                try {
                    val newMovies = remoteDatasourceMovieComedy.getMovieListComedy(page)
                    localDatasourceMovieComedy.saveMovieComedyToDb(newMovies)
                } catch (connect: ConnectException) {
                } catch (network: NetworkErrorException) {
                }
            }
        }
    }
}