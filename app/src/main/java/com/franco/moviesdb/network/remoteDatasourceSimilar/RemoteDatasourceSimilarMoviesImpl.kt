package com.franco.moviesdb.network.remoteDatasourceSimilar

import android.util.Log
import com.franco.moviesdb.database.similarMovies.fromNetToDomain
import com.franco.moviesdb.domain.SimilarMovies
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.util.ALONE_API
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class RemoteDatasourceSimilarMoviesImpl @Inject constructor(
        private val service: ApiService) : RemoteDatasourceSimilarMovies {

    @ExperimentalCoroutinesApi
    override suspend fun getSimilarMovies(movieId: Int, page: Int): List<SimilarMovies> {
        service.let {

            val totalPages = service.getSimilarMovies(movieId, ALONE_API, "en-US", page).totalPages

            val list = service.getSimilarMovies(movieId, ALONE_API, "en-US", page).similarMovies.map {
                it.fromNetToDomain()
            }
            list.forEach {
                it.totalPages = totalPages
            }
            return list

        }
    }
}