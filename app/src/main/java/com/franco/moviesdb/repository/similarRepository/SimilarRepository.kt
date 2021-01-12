package com.franco.moviesdb.repository.similarRepository

import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.domain.SimilarMovies
import kotlinx.coroutines.flow.Flow

interface SimilarRepository {
    fun getSimilarMoviesByMovie(movieId: Int): Flow<List<SimilarMovies>>
    suspend fun checkRequireNewPageSimilarMovies(lastVisible: Int)
}