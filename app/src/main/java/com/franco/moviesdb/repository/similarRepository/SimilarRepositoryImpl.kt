package com.franco.moviesdb.repository.similarRepository

import com.franco.moviesdb.database.similarMovies.localDatasourceSimilar.LocalDatasourceSimilarImpl
import com.franco.moviesdb.domain.SimilarMovies
import com.franco.moviesdb.network.remoteDatasourceSimilar.RemoteDatasourceSimilarMoviesImpl
import kotlinx.coroutines.flow.Flow

class SimilarRepositoryImpl(
        private val localDatasourceSimilar: LocalDatasourceSimilarImpl,
        private val remoteDatasourceSimilar: RemoteDatasourceSimilarMoviesImpl
) : SimilarRepository {
    override fun getSimilarMoviesByMovie(movieId: Int): Flow<List<SimilarMovies>> {
        TODO("Not yet implemented")
    }

    override suspend fun checkRequireNewPageSimilarMovies(lastVisible: Int) {
        TODO("Not yet implemented")
    }
}