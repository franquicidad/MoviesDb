package com.franco.moviesdb.repository.actorsBioRepository

import com.franco.moviesdb.domain.ActorBiographyResponce
import kotlinx.coroutines.flow.Flow

interface actorBioRepository {
    suspend fun getActorBioFromDb(actorId: Int): Flow<ActorBiographyResponce>
    suspend fun retreiveAndAddToDatabase(actorId: Int)
}