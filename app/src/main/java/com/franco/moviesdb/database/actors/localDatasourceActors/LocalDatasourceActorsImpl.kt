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
        return movieDb.actorsDAO().actorsCount()
    }

    override suspend fun getAllCastsI(): Flow<List<ResponceWithActor>> {
        return movieDb.actorsDAO().getAllCast()
    }

    override suspend fun getCastForMovieByIdI(movieId: Int): Flow<List<Actor>> {
//        return movieDb.actorsDAO().getActorsForMovie(movieId).map { actorList ->
//            actorList.map {
//                it.fromDatabaseToDomain()
//            }
//        }
        return
    }

    override suspend fun insertActorList(cast: List<ResponceWithActor>) {
        movieDb.actorsDAO().insertActors(cast)
    }

}