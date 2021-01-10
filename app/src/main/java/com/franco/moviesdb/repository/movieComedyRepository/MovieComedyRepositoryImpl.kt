package com.franco.moviesdb.repository.movieComedyRepository

import android.util.Log
import com.franco.moviesdb.database.localDatasources.movies.localDataSourceMoviecomedy.LocalDataSourceMovieComedyImpl
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.domain.RepositoryImpl
import com.franco.moviesdb.network.remoteDatasourceMoviecomedy.RemoteDatasourceMovieComedyImpl
import com.franco.moviesdb.repository.movieComedyRepository.MovieComedyRepositoryImpl.Companion.PAGE_THRESHOLD

import kotlinx.coroutines.flow.Flow

class MovieComedyRepositoryImpl(
        private val localDatasourceMovieComedy: LocalDataSourceMovieComedyImpl,
        private val remoteDatasourceMovieComedy: RemoteDatasourceMovieComedyImpl
) : MovieComedyRepository {

    companion object {

        const val PAGE_SIZE = 5
        const val PAGE_THRESHOLD = 10

    }

    override fun getMovieListComedyByQuery(query: String): Flow<List<MovieActionDomain>> =
            localDatasourceMovieComedy.getListBySearchBarMovieComedyFromDatabase(query)

    override suspend fun checkRequireNewPageMovieComedy(lastVisible: Int) {
        val size = localDatasourceMovieComedy.movieComedySize()
        if (lastVisible >= size - MovieComedyRepositoryImpl.PAGE_THRESHOLD) {
            val page = size / MovieComedyRepositoryImpl.PAGE_SIZE + 1
            val newMovies = remoteDatasourceMovieComedy.getMovieListComedy(page)
            localDatasourceMovieComedy.saveMovieComedyToDb(newMovies)
        }
    }
}