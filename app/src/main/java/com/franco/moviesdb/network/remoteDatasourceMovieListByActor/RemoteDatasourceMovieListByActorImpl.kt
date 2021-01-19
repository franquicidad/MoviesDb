package com.franco.moviesdb.network.remoteDatasourceMovieListByActor

import com.franco.moviesdb.domain.ActorListMovies
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.network.entityMappers.fromNetworkToDomain
import com.franco.moviesdb.util.ALONE_API
import javax.inject.Inject

class RemoteDatasourceMovieListByActorImpl @Inject constructor(
        private val apiService: ApiService
) : RemoteDatasourceMovieListByActor {
    override suspend fun getMoviesListByActor(actorId: Int): List<ActorListMovies> {
        return apiService.getMoviesFromActor(actorId, ALONE_API).cast.map {
            it.fromNetworkToDomain()
        }
    }
}