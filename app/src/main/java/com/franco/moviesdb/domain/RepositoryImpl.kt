package com.franco.moviesdb.domain

import com.franco.moviesdb.database.LocalDatasourceImpl
import com.franco.moviesdb.network.RemoteDatasourceImpl
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val localDatasource: LocalDatasourceImpl,
    private val remoteDatasource: RemoteDatasourceImpl,
) : Repository {
    companion object {
        const val PAGE_SIZE = 20
        const val PAGE_THRESHOLD = 10
    }


    override fun getMovieListActionByQuery(query: String): Flow<List<MovieActionDomain>> =
        localDatasource.getListBySearchBarMovieActionFromDatabase(query)

    override fun getMovieListComedyByQuery(query: String): Flow<List<MovieActionDomain>> =
        localDatasource.getListBySearchBarMovieComedyFromDatabase(query)


    override fun getTvListActionByQuery(query: String): Flow<List<MovieActionDomain>> =
        localDatasource.getListBySearchBarTvActionFromDatabase(query)

    override fun getTvListComedyByQuery(query: String): Flow<List<MovieActionDomain>> =
        localDatasource.getListBySearchBarTvComedyFromDatabase(query)

    override suspend fun checkRequireNewPageMovieAction(lastVisible: Int) {
        val size = localDatasource.movieActionSize()
        if (lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            val newMovies = remoteDatasource.getMovieListAction(page)
            localDatasource.saveMovieActionToDb(newMovies)
        }
    }

    override suspend fun checkRequireNewPageMovieComedy(lastVisible: Int) {
        val size = localDatasource.movieActionSize()
        if (lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            val newMovies = remoteDatasource.getMovieListComedy(page)
            localDatasource.saveMovieComedyToDb(newMovies)
        }
    }

    override suspend fun checkRequireNewPageTvAction(lastVisible: Int) {
        val size = localDatasource.movieActionSize()
        if (lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            val newMovies = remoteDatasource.getTvListAction(page)
            localDatasource.saveTvActionToDb(newMovies)
        }
    }

    override suspend fun checkRequireNewPageTvComedy(lastVisible: Int) {
        val size = localDatasource.movieActionSize()
        if (lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            val newMovies = remoteDatasource.getTvListComedy(page)
            localDatasource.saveTvComedyToDb(newMovies)
        }
    }
}