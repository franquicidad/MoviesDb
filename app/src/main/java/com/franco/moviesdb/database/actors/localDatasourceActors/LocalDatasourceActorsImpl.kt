package com.franco.moviesdb.database.actors.localDatasourceActors

import com.franco.moviesdb.database.MovieDatabase
import com.franco.moviesdb.database.actors.LocalDatasourceActors
import com.franco.moviesdb.database.actors.model.ResponceWithActor
import com.franco.moviesdb.domain.Actor
import com.franco.moviesdb.network.fromDatabaseToDomain
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDatasourceActorsImpl @Inject constructor(
        private val movieDb: MovieDatabase
) : LocalDatasourceActors {
    override suspend fun actorsSize(): Int {
        return movieDb.moviesDAO().actorsCount()
    }

    override suspend fun getAllCastsI(): Flow<List<ResponceWithActor>> {
        return movieDb.moviesDAO().getAllCast()
    }

    override suspend fun getCastForMovieByIdI(movieId: Int): Flow<List<Actor>> {
        return movieDb.moviesDAO().getActorsForMovie(movieId).map { actorList ->
            actorList.map {
                it.fromDatabaseToDomain()
            }
        }
    }

    override suspend fun insertActorList(cast: List<ResponceWithActor>) {
        movieDb.moviesDAO().insertActors(cast)
    }

}