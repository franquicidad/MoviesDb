package com.franco.moviesdb.domain

import android.util.Log
import com.franco.moviesdb.database.actors.localDatasourceActors.LocalDatasourceActorsImpl
import com.franco.moviesdb.database.actors.remoteDatasourceActors.RemoteDatasourceActorsImpl
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
        private val localDatasource: LocalDatasourceImpl,
        private val remoteDatasource: RemoteDatasourceImpl,
        private val localActorsDatasource: LocalDatasourceActorsImpl,
        private val remoteActorsDatasource: RemoteDatasourceActorsImpl
) : Repository {










    override suspend fun getActorsFromDatabase(movieId: Int): Flow<List<Actor>> {
        return localActorsDatasource.getCastForMovieByIdI(movieId)
    }

    override suspend fun obtainAndSaveToDb(movieId: Int) {
        val list = remoteActorsDatasource.getActorsRemote(movieId)
        Log.i("Cast", "$list")
        localActorsDatasource.insertActorList(list)
    }


}