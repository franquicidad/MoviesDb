package com.franco.moviesdb.database.actorsBiographyLocal


import com.franco.moviesdb.database.MovieDatabase
import com.franco.moviesdb.domain.ActorBiographyResponce
import com.franco.moviesdb.network.entityMappers.fromDatabaseToDomain
import com.franco.moviesdb.network.entityMappers.fromDomainToDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ActorBioLocalDatasourceImpl @Inject constructor(
    private val movieDatabase: MovieDatabase
) : ActorBioLocalDatasource {
    override fun getActorInfo(actorId: Int): ActorBiographyResponce =
            movieDatabase.actorsBioDAO().getActorBioInfo(actorId)?.fromDatabaseToDomain()

    override suspend fun insertActorToDb(actor: ActorBiographyResponce) {
        return movieDatabase.actorsBioDAO().insertActorInfo(actor.fromDomainToDatabase())
    }
}