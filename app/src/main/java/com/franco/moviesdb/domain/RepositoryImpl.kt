package com.franco.moviesdb.domain

import android.util.Log
import com.franco.moviesdb.database.LocalDatasourceImpl
import com.franco.moviesdb.network.RemoteDatasourceImpl
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val localDatasource: LocalDatasourceImpl,
    private val remoteDatasource: RemoteDatasourceImpl,
) : Repository {
    companion object {
        const val PAGE_SIZE = 5
        const val PAGE_THRESHOLD = 10
    }


    override fun getMovieListActionByQuery(query: String): Flow<List<MovieActionDomain>> =
            localDatasource.getListBySearchBarMovieActionFromDatabase(query)

    override fun getMovieListComedyByQuery(query: String): Flow<List<MovieActionDomain>> =
            localDatasource.getListBySearchBarMovieComedyFromDatabase(query)

    override suspend fun getAllActors(movieId: Int): Flow<List<ActorsDomain>> =
            localDatasource.getAllActorsMovieId(movieId)


    override fun getTvListActionByQuery(query: String): Flow<List<MovieActionDomain>> =
            localDatasource.getListBySearchBarTvActionFromDatabase(query)

    override fun getTvListComedyByQuery(query: String): Flow<List<MovieActionDomain>> =
            localDatasource.getListBySearchBarTvComedyFromDatabase(query)

    override suspend fun checkRequireNewPageMovieAction(lastVisible: Int) {
        val size = localDatasource.movieActionSize()
        if (lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            Log.i("page", "Page $page ---->:next page -->${page + 1}")
            val newMovies = remoteDatasource.getMovieListAction(page)
            localDatasource.saveMovieActionToDb(newMovies)
        }
    }

    override suspend fun checkRequireNewPageMovieComedy(lastVisible: Int) {
        Log.i("Comedy", "paginationRepo")
        val size = localDatasource.movieComedySize()
        if (lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            val newMovies = remoteDatasource.getMovieListComedy(page)
            localDatasource.saveMovieComedyToDb(newMovies)
        }
    }

    override suspend fun checkRequireNewPageTvAction(lastVisible: Int) {
        val size = localDatasource.tvActionSize()
        if (lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            val newMovies = remoteDatasource.getTvListAction(page)
            localDatasource.saveTvActionToDb(newMovies)
        }
    }

    override suspend fun checkRequireNewPageTvComedy(lastVisible: Int) {
        val size = localDatasource.tvComedySize()
        if (lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            val tvCom = remoteDatasource.getTvListComedy(page)
            localDatasource.saveTvComedyToDb(tvCom)
        }
    }


    override suspend fun checkRequireNewPageActors(id: Int) {

        val newActors = remoteDatasource.getActorsRemote(id)
        localDatasource.insertActors(newActors)

    }
}