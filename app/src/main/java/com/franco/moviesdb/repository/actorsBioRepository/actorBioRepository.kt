package com.franco.moviesdb.repository.actorsBioRepository

import androidx.lifecycle.LiveData
import com.franco.moviesdb.domain.ActorBiographyResponce
import kotlinx.coroutines.flow.Flow

interface actorBioRepository {
    suspend fun getActorBioFromDb(actorId: Int): Flow<ActorBiographyResponce>
    suspend fun retreiveAndAddToDatabase(actorId: Int)
}