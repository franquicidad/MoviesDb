package com.franco.moviesdb.network.remoteDatasourceTvAction

import com.franco.moviesdb.domain.MovieActionDomain

interface RemoteDatasourceTvAction {
    suspend fun getTvListAction(page: Int): List<MovieActionDomain>

}