package com.franco.moviesdb.repository.similarRepository

import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.domain.SimilarMovies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface SimilarRepository {
    suspend fun getSimilarMoviesByMovie(movieId: Int): Flow<List<SimilarMovies>>

    @ExperimentalCoroutinesApi
    suspend fun checkRequireNewPageSimilarMovies(movieOrTv: String, movieId: Int, lastVisible: Int)
}