package com.franco.moviesdb.database.localDatasources.movies.localDataSourceMoviecomedy

import com.franco.moviesdb.database.MovieDatabase
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.mapFromDatabaseComedyToDomain
import com.franco.moviesdb.network.mapFromDomainToDatabaseComedy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceMovieComedyImpl @Inject constructor(
        private val movieDb: MovieDatabase,

        ) : LocalDataSourceMovieComedy {
    override suspend fun movieComedySize(): Int {
        return movieDb.moviesComedyDAO().movieCountComedy()
    }

    override suspend fun saveMovieComedyToDb(movie: List<MovieActionDomain>) =
            movieDb.moviesComedyDAO().insertMovieComedy(movie.map { movieComedy ->
                movieComedy.mapFromDomainToDatabaseComedy()
            })

    override fun getAllMoviesComedyFromDatabase(): Flow<List<MovieActionDomain>> =
            movieDb.moviesComedyDAO().getAllMoviesComedyByGenre().map { movieComedy ->
                movieComedy.map {
                    it.mapFromDatabaseComedyToDomain()
                }

            }

    override fun getListBySearchBarMovieComedyFromDatabase(query: String): Flow<List<MovieActionDomain>> =
            movieDb.moviesComedyDAO().getListBySearchBarMovieComedy(query).map { movieTable ->
                movieTable.map { it.mapFromDatabaseComedyToDomain() }
            }
}