package com.franco.moviesdb.repository.movieActionRepository

import com.franco.moviesdb.domain.MovieActionDomain
import kotlinx.coroutines.flow.Flow

interface MovieActionRepository {
    fun getMovieListActionByQuery(query: String): Flow<List<MovieActionDomain>>
    suspend fun checkRequireNewPageMovieAction(lastVisible: Int)

}