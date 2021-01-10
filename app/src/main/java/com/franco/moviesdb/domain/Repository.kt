package com.franco.moviesdb.domain

import com.franco.moviesdb.database.actors.model.ResponceWithActor
import kotlinx.coroutines.flow.Flow

interface Repository {
    //    fun getAllMovies(): Flow<List<MovieActionDomain>>
//    fun getAllTv(): Flow<List<MovieActionDomain>>





    /**
     * Get and save Actors
     */

    suspend fun getActorsFromDatabase(movieId: Int): Flow<List<ResponceWithActor>>

    suspend fun obtainAndSaveToDb(movieId: Int)


}