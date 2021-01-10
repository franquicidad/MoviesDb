package com.franco.moviesdb.network.remoteDatasourceTvComedy

import com.franco.moviesdb.domain.MovieActionDomain

interface RemoteDataSourceTvComedy {
    suspend fun getTvListComedy(page: Int): List<MovieActionDomain>

}