package com.franco.moviesdb.database.actorsBiographyLocal

import androidx.lifecycle.LiveData
import com.franco.moviesdb.domain.ActorBiographyResponce
import kotlinx.coroutines.flow.Flow

interface ActorBioLocalDatasource {
    fun getActorInfo(actorId: Int): ActorBiographyResponce
    suspend fun insertActorToDb(actor: ActorBiographyResponce)
}