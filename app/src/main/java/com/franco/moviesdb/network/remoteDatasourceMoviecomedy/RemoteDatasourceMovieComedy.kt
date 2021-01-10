package com.franco.moviesdb.network.remoteDatasourceMoviecomedy

import com.franco.moviesdb.domain.MovieActionDomain

interface RemoteDatasourceMovieComedy {
    suspend fun getMovieListComedy(page: Int): List<MovieActionDomain>

}