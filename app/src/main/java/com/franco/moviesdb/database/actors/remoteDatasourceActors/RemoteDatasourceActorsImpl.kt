package com.franco.moviesdb.database.actors.remoteDatasourceActors

import com.franco.moviesdb.database.actors.model.Actor
import com.franco.moviesdb.database.actors.model.ResponceWithActor
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.network.fromCastToResponce
import com.franco.moviesdb.network.fromNetworkToDatabase
import com.franco.moviesdb.util.ALONE_API
import javax.inject.Inject

class RemoteDatasourceActorsImpl @Inject constructor(
        private val service: ApiService

) : RemoteDatasourceActors {

    override suspend fun getActorsRemote(id: Int): List<ResponceWithActor> =
            service.getActorsByMovie(id, ALONE_API)
                    .cast.map {
                        it.fromNetworkToDatabase()
                    }


}