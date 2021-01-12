package com.franco.moviesdb.database.similarMovies.localDatasourceSimilar

import com.franco.moviesdb.domain.SimilarMovies
import kotlinx.coroutines.flow.Flow

interface LocalDatasourceSimilar {
    suspend fun size(): Int
    suspend fun getAllSimilarByMovieId(movieId: Int): Flow<List<SimilarMovies>>
    suspend fun insertSimilar(movies: List<SimilarMovies>)
}