package com.franco.moviesdb.domain

import com.franco.moviesdb.database.actors.model.ResponceWithActor
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

    /**
     * Get and save Actors
     */

    suspend fun getActorsFromDatabase(movieId: Int): Flow<List<ResponceWithActor>>

    suspend fun obtainAndSaveToDb(movieId: Int)


}