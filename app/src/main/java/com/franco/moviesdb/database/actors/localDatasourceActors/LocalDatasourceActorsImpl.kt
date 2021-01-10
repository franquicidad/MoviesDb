package com.franco.moviesdb.database.actors.localDatasourceActors

import com.franco.moviesdb.database.MovieDatabase
import com.franco.moviesdb.database.actors.LocalDatasourceActors
import com.franco.moviesdb.database.actors.model.ResponceWithActor
import com.franco.moviesdb.domain.Actor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDatasourceActorsImpl @Inject constructor(
        private val movieDb: MovieDatabase
) : LocalDatasourceActors {
    override suspend fun actorsSize(): Int {
        return movieDb.moviesActionDAO().actorsCount()
    }

    override suspend fun getAllCastsI(): Flow<List<ResponceWithActor>> {
        return movieDb.moviesActionDAO().getAllCast()
    }

    override suspend fun getCastForMovieByIdI(movieId: Int): Flow<List<Actor>> {
        return movieDb.moviesActionDAO().getActorsForMovie(movieId).map { actorList ->
            actorList.map {
                it.fromDatabaseToDomain()
            }
        }
    }

    override suspend fun insertActorList(cast: List<ResponceWithActor>) {
        movieDb.moviesActionDAO().insertActors(cast)
    }

}