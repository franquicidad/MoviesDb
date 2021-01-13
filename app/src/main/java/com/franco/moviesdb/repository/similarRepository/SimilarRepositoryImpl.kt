package com.franco.moviesdb.repository.similarRepository

import com.franco.moviesdb.database.similarMovies.localDatasourceSimilar.LocalDatasourceSimilarImpl
import com.franco.moviesdb.domain.SimilarMovies
import com.franco.moviesdb.network.remoteDatasourceSimilar.RemoteDatasourceSimilarMoviesImpl
import com.franco.moviesdb.repository.movieActionRepository.MovieActionRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SimilarRepositoryImpl(
        private val localDatasourceSimilar: LocalDatasourceSimilarImpl,
        private val remoteDatasourceSimilar: RemoteDatasourceSimilarMoviesImpl
) : SimilarRepository {

    companion object {
        const val PAGE_SIZE = 5
        const val PAGE_THRESHOLD = 10
    }

    override suspend fun getSimilarMoviesByMovie(movieId: Int): Flow<List<SimilarMovies>> {
        return localDatasourceSimilar.getAllSimilarByMovieId(movieId)
    }

    @ExperimentalCoroutinesApi
    override suspend fun checkRequireNewPageSimilarMovies(movieId: Int, lastVisible: Int) {
        val size = localDatasourceSimilar.size()
        if (lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            val newMovies = remoteDatasourceSimilar.getSimilarMovies(movieId, page)
            localDatasourceSimilar.insertSimilar(newMovies)
        }
    }
}