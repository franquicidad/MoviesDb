package com.franco.moviesdb.network.remoteDatasourceMovieAction

import com.franco.moviesdb.domain.MovieActionDomain

interface RemoteDatasourceMovieAction {
    suspend fun getMovieListAction(page: Int): List<MovieActionDomain>

}