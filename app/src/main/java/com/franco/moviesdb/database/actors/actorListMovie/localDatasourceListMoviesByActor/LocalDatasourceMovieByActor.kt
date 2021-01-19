package com.franco.moviesdb.database.actors.actorListMovie.localDatasourceListMoviesByActor

import com.franco.moviesdb.database.actors.model.Actor
import com.franco.moviesdb.domain.ActorListMovies
import kotlinx.coroutines.flow.Flow

interface LocalDatasourceMovieByActor {
    fun getAllMoviesByActorId(actorId: Int): Flow<List<ActorListMovies>>
    suspend fun insertActors(actor: List<ActorListMovies>)


}