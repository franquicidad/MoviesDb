package com.franco.moviesdb.database.actors.actorListMovie.localDatasourceListMoviesByActor

import com.franco.moviesdb.database.MovieDatabase
import com.franco.moviesdb.domain.ActorListMovies
import com.franco.moviesdb.network.entityMappers.fromDatabaseToDomain
import com.franco.moviesdb.network.entityMappers.fromDomainToDatabase
import com.franco.moviesdb.network.fromDatabaseToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDatasourceMovieByActorImpl @Inject constructor(
        private val movieDb: MovieDatabase
) : LocalDatasourceMovieByActor {
    override fun getAllMoviesByActorId(actorId: Int): Flow<List<ActorListMovies>> {
        val list = movieDb.movieListByActorDAO().getAllMoviesByActorId(actorId).map {
            it.map {
                it.fromDatabaseToDomain()
            }

        }
        return list
    }

    override suspend fun insertActors(actor: List<ActorListMovies>) {
        return movieDb.movieListByActorDAO().insertActors(actor.map {
            it.fromDomainToDatabase()
        })
    }
}