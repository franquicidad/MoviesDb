package com.franco.moviesdb.database.localDatasources.movies.localdatasourceMovieAction

import com.franco.moviesdb.database.MovieDatabase
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.mapDatabaseToDomain
import com.franco.moviesdb.network.mapFromDomainToDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDatasourceMoviesActionImpl @Inject constructor(
        private val movieDb: MovieDatabase
) : LocalDatasourceMoviesAction {

    override suspend fun movieActionSize(): Int {
        return movieDb.moviesActionDAO().movieCountAction()
    }

    override suspend fun saveMovieActionToDb(movie: List<MovieActionDomain>) =
            movieDb.moviesActionDAO().insertMovieAction(movie.map {
                it.mapFromDomainToDatabase()
            })

    override fun getAllMoviesActionFromDatabase(): Flow<List<MovieActionDomain>> =
            movieDb.moviesActionDAO().getAllMoviesActionByGenre().map { movies ->
                movies.map {
                    it.mapDatabaseToDomain()
                }
            }

    override fun getListBySearchBarMovieActionFromDatabase(query: String): Flow<List<MovieActionDomain>> =
            movieDb.moviesActionDAO().getListBySearchBarMovieAction(query).map { moviesFilter ->
                moviesFilter.map {
                    it.mapDatabaseToDomain()
                }
            }

}