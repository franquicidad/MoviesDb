package com.franco.moviesdb.repository.movieActionRepository

import com.franco.moviesdb.database.localDatasources.movies.localdatasourceMovieAction.LocalDatasourceMoviesActionImpl
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.remoteDatasourceMovieAction.RemoteDatasourceMovieActionImpl
import kotlinx.coroutines.flow.Flow

class MovieActionRepositoryImpl(
        private val localDatasourceMoviesAction: LocalDatasourceMoviesActionImpl,
        private val remoteDatasourceMoviesAction: RemoteDatasourceMovieActionImpl
) : MovieActionRepository {
    companion object {
        const val PAGE_SIZE = 5
        const val PAGE_THRESHOLD = 10
    }
    override fun getMovieListActionByQuery(query: String): Flow<List<MovieActionDomain>> =
            localDatasourceMoviesAction.getListBySearchBarMovieActionFromDatabase(query)

    override suspend fun checkRequireNewPageMovieAction(lastVisible: Int) {
        val size = localDatasourceMoviesAction.movieActionSize()
        if (lastVisible >= size - MovieActionRepositoryImpl.PAGE_THRESHOLD) {
            val page = size / MovieActionRepositoryImpl.PAGE_SIZE + 1
            val newMovies = remoteDatasourceMoviesAction.getMovieListAction(page)
            localDatasourceMoviesAction.saveMovieActionToDb(newMovies)
        }
    }
}