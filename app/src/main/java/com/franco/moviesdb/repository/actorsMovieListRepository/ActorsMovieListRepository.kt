package com.franco.moviesdb.repository.actorsMovieListRepository

import com.franco.moviesdb.domain.ActorListMovies
import kotlinx.coroutines.flow.Flow

interface ActorsMovieListRepository {
    suspend fun getAllMoviesByActorId(actorId: Int): Flow<List<ActorListMovies>>
    suspend fun retreiveFromNetAddToDB(actorId: Int)
}