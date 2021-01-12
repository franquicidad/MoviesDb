package com.franco.moviesdb.repository.actorsRepository

import com.franco.moviesdb.database.actors.localDatasourceActors.LocalDatasourceActorsImpl
import com.franco.moviesdb.database.actors.remoteDatasourceActors.RemoteDatasourceActorsImpl
import com.franco.moviesdb.domain.Actor
import kotlinx.coroutines.flow.Flow

class ActorsRepository(
        private val localDatasourceActors: LocalDatasourceActorsImpl,
        private val remoteDatasourceActors: RemoteDatasourceActorsImpl
) {

    suspend fun getAllActorsByMovie(movieId: Int): Flow<List<Actor>> {
        val actors = localDatasourceActors.getAllActorsByMovieId(movieId)
        return actors
    }

    suspend fun addActorsByMovie(movieId: Int) {
        val actors = remoteDatasourceActors.getActorsRemote(movieId)

        localDatasourceActors.insertActorList(actors)


    }
}