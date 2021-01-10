package com.franco.moviesdb.repository.movieComedyRepository

import com.franco.moviesdb.domain.MovieActionDomain
import kotlinx.coroutines.flow.Flow

interface MovieComedyRepository {
    fun getMovieListComedyByQuery(query: String): Flow<List<MovieActionDomain>>
    suspend fun checkRequireNewPageMovieComedy(lastVisible: Int)

}