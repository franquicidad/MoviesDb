package com.franco.moviesdb.network.remoteDatasourceMoviecomedy

import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.network.mapMovieToDomain
import com.franco.moviesdb.util.ALONE_API
import com.franco.moviesdb.util.APPEND_MOVIE
import com.franco.moviesdb.util.COMEDY
import com.franco.moviesdb.util.POPULARITY
import javax.inject.Inject

class RemoteDatasourceMovieComedyImpl @Inject constructor(
        private val service: ApiService

) : RemoteDatasourceMovieComedy {
    override suspend fun getMovieListComedy(page: Int): List<MovieActionDomain> =
            service.getMoviesComedy(
                    APPEND_MOVIE, POPULARITY,
                    ALONE_API, COMEDY, page
            )
                    .movieActionList.map { movieComedy ->
                        movieComedy.mapMovieToDomain()

                    }
}