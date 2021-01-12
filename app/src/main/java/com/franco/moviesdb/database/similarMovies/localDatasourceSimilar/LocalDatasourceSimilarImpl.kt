package com.franco.moviesdb.database.similarMovies.localDatasourceSimilar

import com.franco.moviesdb.database.similarMovies.SimilarDao
import com.franco.moviesdb.database.similarMovies.fromDbToDomain
import com.franco.moviesdb.database.similarMovies.fromDomainToDB
import com.franco.moviesdb.domain.SimilarMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDatasourceSimilarImpl @Inject constructor(
        private val similarDao: SimilarDao
) : LocalDatasourceSimilar {
    override suspend fun size(): Int {
        return similarDao.similarCount()
    }

    override suspend fun getAllSimilarByMovieId(movieId: Int): Flow<List<SimilarMovies>> =
            similarDao.getAllSimilarByMovieId(movieId).map {
                it.map {
                    it.fromDbToDomain()
                }
            }

    override suspend fun insertSimilar(movies: List<SimilarMovies>) {
        return similarDao.insertSimilar(movies = movies.map {
            it.fromDomainToDB()
        })
    }

}