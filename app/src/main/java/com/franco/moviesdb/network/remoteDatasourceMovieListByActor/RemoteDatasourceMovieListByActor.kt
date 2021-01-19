package com.franco.moviesdb.network.remoteDatasourceMovieListByActor

import com.franco.moviesdb.domain.ActorListMovies


interface RemoteDatasourceMovieListByActor {
    suspend fun getMoviesListByActor(actorId: Int): List<ActorListMovies>
}