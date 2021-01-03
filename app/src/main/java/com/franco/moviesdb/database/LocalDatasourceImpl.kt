package com.franco.moviesdb.database

import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDatasourceImpl @Inject constructor(
    private val movieDb: MovieDatabase,

    ) : LocalDatasource {


    override suspend fun movieActionSize(): Int {
        return movieDb.moviesDAO().movieCountAction()
    }

    override suspend fun movieComedySize(): Int {
        return movieDb.moviesDAO().movieCountAction()
    }

    override suspend fun tvActionSize(): Int {
        return movieDb.moviesDAO().tvCountAction()
    }

    override suspend fun tvComedySize(): Int {
        return movieDb.moviesDAO().tvCountAction()
    }


    override suspend fun saveMovieActionToDb(movie: List<MovieActionDomain>) =
        movieDb.moviesDAO().insertMovieAction(movie.map {
            it.mapFromDomainToDatabase()
        })

    override suspend fun saveMovieComedyToDb(movie: List<MovieActionDomain>) =
        movieDb.moviesDAO().insertMovieComedy(movie.map { movieComedy ->
            movieComedy.mapFromDomainToDatabaseComedy()
        })


    override suspend fun saveTvActionToDb(tv: List<MovieActionDomain>) {
        movieDb.moviesDAO().insertTvAction(tv.map {
            it.mapFromDomainToTvDatabase()
        })
    }

    override suspend fun saveTvComedyToDb(tv: List<MovieActionDomain>) {
        movieDb.moviesDAO().insertTvComedy(tv.map {
            it.mapFromDomainToTvDatabaseComedy()
        })
    }

    override fun getAllMoviesActionFromDatabase(): Flow<List<MovieActionDomain>> =
        movieDb.moviesDAO().getAllMoviesActionByGenre().map { movies ->
            movies.map {
                it.mapDatabaseToDomain()
            }
        }

    override fun getAllMoviesComedyFromDatabase(): Flow<List<MovieActionDomain>> =
        movieDb.moviesDAO().getAllMoviesComedyByGenre().map { movieComedy ->
            movieComedy.map {
                it.mapFromDatabaseComedyToDomain()
            }

        }

    override fun getAllTvSeriesActionFromDatabase(): Flow<List<MovieActionDomain>> =
        movieDb.moviesDAO().getAllTvActionByGenre().map { tv ->
            tv.map {
                it.mapTvDatabaseToDomain()
            }

        }

    override fun getAllTvSeriesComedyFromDatabase(): Flow<List<MovieActionDomain>> =
        movieDb.moviesDAO().getAllTvActionByGenre().map { tv ->
            tv.map {
                it.mapTvDatabaseToDomain()
            }

        }

    override fun getListBySearchBarMovieActionFromDatabase(query: String): Flow<List<MovieActionDomain>> =
        movieDb.moviesDAO().getListBySearchBarMovieAction(query).map { moviesFiltered ->
            moviesFiltered.map {
                it.mapDatabaseToDomain()
            }

        }

    override fun getListBySearchBarMovieComedyFromDatabase(query: String): Flow<List<MovieActionDomain>> =
        movieDb.moviesDAO().getListBySearchBarMovieComedy(query).map { movieTable ->
            movieTable.map { it.mapFromDatabaseComedyToDomain() }
        }


    override fun getListBySearchBarTvActionFromDatabase(query: String): Flow<List<MovieActionDomain>> =
        movieDb.moviesDAO().getListBySearchBarTvAction(query).map { tvFiltered ->
            tvFiltered.map {
                it.mapTvDatabaseToDomain()
            }
        }

    override fun getListBySearchBarTvComedyFromDatabase(query: String): Flow<List<MovieActionDomain>> =
        movieDb.moviesDAO().getListBySearchBarTvAction(query).map { tvFiltered ->
            tvFiltered.map {
                it.mapTvDatabaseToDomain()
            }
        }
}