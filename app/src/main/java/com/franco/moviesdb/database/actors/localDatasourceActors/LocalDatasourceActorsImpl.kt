package com.franco.moviesdb.database.actors.localDatasourceActors

import com.franco.moviesdb.database.MovieDatabase
import com.franco.moviesdb.database.actors.LocalDatasourceActors
import com.franco.moviesdb.domain.Actor
import com.franco.moviesdb.network.fromDatabaseToDomain
import com.franco.moviesdb.network.fromDomainToDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDatasourceActorsImpl @Inject constructor(
        private val movieDb: MovieDatabase
) : LocalDatasourceActors {
    override suspend fun actorsSize(): Int {
        return movieDb.actorsDAO().actorsCount()
    }

    override suspend fun getAllActorsByMovieId(movieId: Int): Flow<List<Actor>> =
            movieDb.actorsDAO().getAllActorsByMovieId(movieId).map {
                it.map {
                    it.fromDatabaseToDomain()
                }
            }


    override suspend fun insertActorList(cast: List<Actor>) {
        movieDb.actorsDAO().insertActors(cast.map {
            it.fromDomainToDatabase()
        })
    }
}