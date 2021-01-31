package com.franco.moviesdb.repository.actorsRepository

import android.util.Log
import com.franco.moviesdb.database.actors.localDatasourceActors.LocalDatasourceActorsImpl
import com.franco.moviesdb.database.actors.remoteDatasourceActors.RemoteDatasourceActorsImpl
import com.franco.moviesdb.domain.Actor
import com.franco.moviesdb.util.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ActorsRepository(
        private val localDatasourceActors: LocalDatasourceActorsImpl,
        private val remoteDatasourceActors: RemoteDatasourceActorsImpl,
        private val isInternetAvailable: NetworkUtils
) {

    suspend fun getAllActorsByMovie(movieId: Int): Flow<List<Actor>> {
        val actors = localDatasourceActors.getAllActorsByMovieId(movieId)
        return actors
    }

    suspend fun addActorsByMovie(movieOrTv: String, movieId: Int) {
        if (isInternetAvailable.isInternetAvailable()) {
            val actors = remoteDatasourceActors.getActorsRemote(movieOrTv, movieId)

            localDatasourceActors.insertActorList(actors)
        }


    }
}