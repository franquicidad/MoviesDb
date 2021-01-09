package com.franco.moviesdb.domain

import android.util.Log
import com.franco.moviesdb.database.LocalDatasourceImpl
import com.franco.moviesdb.database.actors.localDatasourceActors.LocalDatasourceActorsImpl
import com.franco.moviesdb.database.actors.model.ResponceWithActor
import com.franco.moviesdb.database.actors.remoteDatasourceActors.RemoteDatasourceActorsImpl
import com.franco.moviesdb.network.RemoteDatasourceImpl
import com.franco.moviesdb.network.fromDatabaseToDomain
import com.franco.moviesdb.network.fromDomainToDatabase
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
        private val localDatasource: LocalDatasourceImpl,
        private val remoteDatasource: RemoteDatasourceImpl,
        private val localActorsDatasource: LocalDatasourceActorsImpl,
        private val remoteActorsDatasource: RemoteDatasourceActorsImpl
) : Repository {

    companion object {
        const val PAGE_SIZE = 5
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
        val page = size / PAGE_SIZE + 1

        if (lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            val newMovies = remoteDatasource.getTvListAction(page)
            localDatasource.saveTvActionToDb(newMovies)
        }
    }

    override suspend fun checkRequireNewPageTvComedy(lastVisible: Int) {
        val size = localDatasource.tvComedySize()
        val page = size / PAGE_SIZE + 1

        if (lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            val tvCom = remoteDatasource.getTvListComedy(page)
            localDatasource.saveTvComedyToDb(tvCom)
        }
    }

    override suspend fun getActorsFromDatabase(movieId: Int): Flow<List<Actor>> {
        return localActorsDatasource.getCastForMovieByIdI(movieId)
    }

    override suspend fun obtainAndSaveToDb(movieId: Int) {
        val list = remoteActorsDatasource.getActorsRemote(movieId)
        Log.i("Cast", "$list")
        localActorsDatasource.insertActorList(list)
    }


}