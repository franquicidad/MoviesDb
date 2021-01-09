package com.franco.moviesdb.database.actors.remoteDatasourceActors

import com.franco.moviesdb.database.actors.model.Actor
import com.franco.moviesdb.database.actors.model.ResponceWithActor

interface RemoteDatasourceActors {

    suspend fun getActorsRemote(id: Int): List<ResponceWithActor>


}