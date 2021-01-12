package com.franco.moviesdb.network.remoteDatasourceSimilar

import com.franco.moviesdb.domain.SimilarMovies

interface RemoteDatasourceSimilarMovies {
    suspend fun getSimilarMovies(movieId: Int, page: Int): List<SimilarMovies>
}