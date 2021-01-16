package com.franco.moviesdb.network.remoteDatasourceSimilar

import android.util.Log
import com.franco.moviesdb.database.similarMovies.fromNetToDomain
import com.franco.moviesdb.domain.SimilarMovies
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.util.ALONE_API
import com.franco.moviesdb.util.APPEND_MOVIE
import com.franco.moviesdb.util.APPEND_TV
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class RemoteDatasourceSimilarMoviesImpl @Inject constructor(
        private val service: ApiService) : RemoteDatasourceSimilarMovies {

    @ExperimentalCoroutinesApi
    override suspend fun getSimilarMovies(
        movieOrTv: String,
        movieId: Int,
        page: Int
    ): List<SimilarMovies> {
        var movieOrParam = movieOrTv
        if (movieOrParam.equals("movie")) {
            movieOrParam = APPEND_MOVIE
        } else {
            movieOrParam = APPEND_TV
        }

        val totalPages =
            service.getSimilarMovies(movieOrParam, movieId, ALONE_API, "en-US", page).totalPages

        val list = service.getSimilarMovies(
            movieOrParam,
            movieId,
            ALONE_API,
            "en-US",
            page
        ).similarMovies.map {
            it.fromNetToDomain()
        }
        list.forEach {
            it.totalPages = totalPages
        }
        return list


    }
}