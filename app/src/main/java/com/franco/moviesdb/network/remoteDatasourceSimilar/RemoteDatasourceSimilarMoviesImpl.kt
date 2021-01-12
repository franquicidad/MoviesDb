package com.franco.moviesdb.network.remoteDatasourceSimilar

import com.franco.moviesdb.database.similarMovies.fromNetToDomain
import com.franco.moviesdb.domain.SimilarMovies
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.util.ALONE_API
import javax.inject.Inject

class RemoteDatasourceSimilarMoviesImpl @Inject constructor(
        private val service: ApiService
) : RemoteDatasourceSimilarMovies {
    override suspend fun getSimilarMovies(movieId: Int, page: Int): List<SimilarMovies> {
        return service.getSimilarMovies(movieId, ALONE_API, page = page).similarMovies.map { similar ->
            similar.fromNetToDomain()
        }
    }
}