package com.franco.moviesdb.network

import com.franco.moviesdb.domain.ActorsDomain
import com.franco.moviesdb.domain.MovieActionDomain

interface RemoteDatasource {
    suspend fun getMovieListAction(page: Int): List<MovieActionDomain>
    suspend fun getMovieListComedy(page: Int): List<MovieActionDomain>
    suspend fun getTvListAction(page: Int): List<MovieActionDomain>
    suspend fun getTvListComedy(page: Int): List<MovieActionDomain>
    suspend fun getActorsRemote(id: Int): List<ActorsDomain>
}