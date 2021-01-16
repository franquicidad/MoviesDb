package com.franco.moviesdb.network.actorsBiographyRemote

import com.franco.moviesdb.domain.ActorBiographyResponce
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.network.entityMappers.fromNetworkToDomain
import com.franco.moviesdb.util.ALONE_API
import javax.inject.Inject

class ActorBioRemoteDatasourceImpl @Inject constructor(
    private val apiService: ApiService
) : ActorBioRemoteDatasource {
    override suspend fun getActorBiographyRemote(actorId: Int): ActorBiographyResponce {
        return apiService.getActorBiography(actorId, ALONE_API).fromNetworkToDomain()
    }
}