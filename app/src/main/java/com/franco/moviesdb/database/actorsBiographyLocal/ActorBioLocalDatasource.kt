package com.franco.moviesdb.database.actorsBiographyLocal

import com.franco.moviesdb.domain.ActorBiographyResponce
import kotlinx.coroutines.flow.Flow

interface ActorBioLocalDatasource {
    fun getActorInfo(actorId: Int): Flow<ActorBiographyResponce>
    suspend fun insertActorToDb(actor: ActorBiographyResponce)
}