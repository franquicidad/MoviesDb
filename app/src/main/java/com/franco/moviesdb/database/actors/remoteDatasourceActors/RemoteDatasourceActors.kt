package com.franco.moviesdb.database.actors.remoteDatasourceActors

import com.franco.moviesdb.domain.Actor


interface RemoteDatasourceActors {

    suspend fun getActorsRemote(movieOrTv: String, movieId: Int): List<Actor>
    //suspend fun getActorsRemoteTv(movieId: Int): List<Actor>


}