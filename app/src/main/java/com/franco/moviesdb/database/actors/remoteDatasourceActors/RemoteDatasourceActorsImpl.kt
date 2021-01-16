package com.franco.moviesdb.database.actors.remoteDatasourceActors


import android.util.Log
import com.franco.moviesdb.domain.Actor
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.network.fromNetworkToDomain
import com.franco.moviesdb.util.ALONE_API
import com.franco.moviesdb.util.APPEND_MOVIE
import com.franco.moviesdb.util.APPEND_TV
import javax.inject.Inject

class RemoteDatasourceActorsImpl @Inject constructor(
        private val service: ApiService

) : RemoteDatasourceActors {
    override suspend fun getActorsRemote(movieOrTv: String, movieId: Int): List<Actor> {
        var movieOrParam = movieOrTv
        if (movieOrParam.equals("movie")) {
            movieOrParam = APPEND_MOVIE
        } else {
            movieOrParam = APPEND_TV
        }

        val list2 = service.getActorsByMovie(movieOrParam, movieId, ALONE_API).cast.map {
            it.fromNetworkToDomain()
        }
        list2.forEach {
            it.movieId = movieId

        }
        return list2
    }

//    override suspend fun getActorsRemoteTv(movieId: Int): List<Actor> {
//        val listTv = service.getActorsByTv(movieId, ALONE_API).cast.map {
//            it.fromNetworkToDomain()
//        }
//        listTv.forEach {
//            it.movieId = movieId
//
//        }
//        return listTv
//    }
}