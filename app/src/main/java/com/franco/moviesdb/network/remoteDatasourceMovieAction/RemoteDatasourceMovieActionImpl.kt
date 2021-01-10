package com.franco.moviesdb.network.remoteDatasourceMovieAction

import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.network.mapMovieToDomain
import com.franco.moviesdb.util.ACTION
import com.franco.moviesdb.util.ALONE_API
import com.franco.moviesdb.util.APPEND_MOVIE
import com.franco.moviesdb.util.POPULARITY
import javax.inject.Inject

class RemoteDatasourceMovieActionImpl @Inject constructor(
        private val service: ApiService

) : RemoteDatasourceMovieAction {
    override suspend fun getMovieListAction(page: Int): List<MovieActionDomain> =
            service.getMoviesAction(
                    APPEND_MOVIE,
                    POPULARITY,
                    ALONE_API,
                    ACTION,
                    page
            ).movieActionList.map {
                it.mapMovieToDomain()
            }
}