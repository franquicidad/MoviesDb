package com.franco.moviesdb.database.similarMovies.localDatasourceSimilar

import com.franco.moviesdb.database.MovieDatabase
import com.franco.moviesdb.database.similarMovies.SimilarDao
import com.franco.moviesdb.database.similarMovies.fromDbToDomain
import com.franco.moviesdb.database.similarMovies.fromDomainToDB
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.domain.SimilarMovies
import com.franco.moviesdb.network.mapFromDomainToDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDatasourceSimilarImpl @Inject constructor(
        private val movieDb: MovieDatabase
) : LocalDatasourceSimilar {
    override suspend fun size(): Int {
        return movieDb.similarDAO().similarCount()
    }

    override suspend fun getAllSimilarByMovieId(movieId: Int): Flow<List<SimilarMovies>> =
            movieDb.similarDAO().getAllSimilarByMovieId(movieId).map {
                it.map {
                    it.fromDbToDomain()
                }
            }

    override suspend fun insertSimilar(movies: List<SimilarMovies>) {
        return movieDb.similarDAO().insertSimilar(movies = movies.map {
            it.fromDomainToDB()
        })
    }

}