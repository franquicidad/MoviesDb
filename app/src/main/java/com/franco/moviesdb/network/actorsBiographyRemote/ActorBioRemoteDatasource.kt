package com.franco.moviesdb.network.actorsBiographyRemote

import com.franco.moviesdb.domain.ActorBiographyResponce

interface ActorBioRemoteDatasource {
    suspend fun getActorBiographyRemote(actorId: Int): ActorBiographyResponce
}