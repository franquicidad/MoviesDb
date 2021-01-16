package com.franco.moviesdb.network.remoteDatasourceSimilar

import com.franco.moviesdb.domain.SimilarMovies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

interface RemoteDatasourceSimilarMovies {
    @ExperimentalCoroutinesApi
    suspend fun getSimilarMovies(movieOrTv: String, movieId: Int, page: Int): List<SimilarMovies>
}