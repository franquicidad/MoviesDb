package com.franco.moviesdb.repository.similarRepository

import android.accounts.NetworkErrorException
import android.util.Log
import com.franco.moviesdb.database.similarMovies.fromDbToDomain
import com.franco.moviesdb.database.similarMovies.fromDomainToDB
import com.franco.moviesdb.database.similarMovies.localDatasourceSimilar.LocalDatasourceSimilarImpl
import com.franco.moviesdb.domain.SimilarMovies
import com.franco.moviesdb.network.remoteDatasourceSimilar.RemoteDatasourceSimilarMoviesImpl
import com.franco.moviesdb.repository.movieActionRepository.MovieActionRepositoryImpl
import com.franco.moviesdb.util.APPEND_MOVIE
import com.franco.moviesdb.util.APPEND_TV
import com.franco.moviesdb.util.NetworkUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.net.ConnectException
import java.net.UnknownHostException

class SimilarRepositoryImpl(
        private val localDatasourceSimilar: LocalDatasourceSimilarImpl,
        private val remoteDatasourceSimilar: RemoteDatasourceSimilarMoviesImpl,
        private val isInternetAvailable: NetworkUtils

) : SimilarRepository {

    companion object {
        const val PAGE_SIZE = 5
        const val PAGE_THRESHOLD = 10
    }

    override suspend fun getSimilarMoviesByMovie(movieId: Int): Flow<List<SimilarMovies>> {
        val someId = movieId
        return localDatasourceSimilar.getAllSimilarByMovieId(someId)
    }

    @ExperimentalCoroutinesApi
    override suspend fun checkRequireNewPageSimilarMovies(
        movieOrTv: String,
        movieId: Int,
        lastVisible: Int
    ) {
        if (isInternetAvailable.isInternetAvailable()) {
            val size = localDatasourceSimilar.size()
            if (size > 0) {
                var movieOrParam = movieOrTv
                if (movieOrParam.equals("movie")) {
                    movieOrParam = APPEND_MOVIE
                } else {
                    movieOrParam = APPEND_TV
                }

                val newSimilarMovies =
                        remoteDatasourceSimilar.getSimilarMovies(movieOrParam, movieId, 1)
                val newDatabaseRelatedMovies = newSimilarMovies.map {
                    it.fromDomainToDB()
                }
                newDatabaseRelatedMovies.forEach {
                    it.relatedToMovieId = movieId
                }
                val finalDomainRelatedDb: List<SimilarMovies> = newDatabaseRelatedMovies.map {
                    it.fromDbToDomain()
                }
                localDatasourceSimilar.insertSimilar(finalDomainRelatedDb)
            }
            if (lastVisible >= size - PAGE_THRESHOLD) {
                var movieOrParam = movieOrTv
                if (movieOrParam.equals("movie")) {
                    movieOrParam = APPEND_MOVIE
                } else {
                    movieOrParam = APPEND_TV
                }
                val page = size / PAGE_SIZE + 1
                val theId = movieId
                try {
                    val newMovies = remoteDatasourceSimilar.getSimilarMovies(movieOrParam, movieId, page)
                    val databaseMovie = newMovies.map {
                        it.fromDomainToDB()
                    }
                    databaseMovie.forEach {
                        it.relatedToMovieId = movieId
                    }
                    val finalDomainDb: List<SimilarMovies> = databaseMovie.map {
                        it.fromDbToDomain()
                    }
                    Log.i("SimId", "$finalDomainDb")
                    localDatasourceSimilar.insertSimilar(finalDomainDb)
                } catch (connection: ConnectException) {
                } catch (network: NetworkErrorException) {
                } catch (unknown: UnknownHostException) {
                }
            }
        }
    }
}