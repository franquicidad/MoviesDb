package com.franco.moviesdb.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    //    fun getAllMovies(): Flow<List<MovieActionDomain>>
//    fun getAllTv(): Flow<List<MovieActionDomain>>
    fun getMovieListActionByQuery(query: String): Flow<List<MovieActionDomain>>
    fun getMovieListComedyByQuery(query: String): Flow<List<MovieActionDomain>>

    fun getTvListActionByQuery(query: String): Flow<List<MovieActionDomain>>
    fun getTvListComedyByQuery(query: String): Flow<List<MovieActionDomain>>

    suspend fun checkRequireNewPageMovieAction(lastVisible: Int)
    suspend fun checkRequireNewPageMovieComedy(lastVisible: Int)

    suspend fun checkRequireNewPageTvAction(lastVisible: Int)
    suspend fun checkRequireNewPageTvComedy(lastVisible: Int)


}