package com.franco.moviesdb.database.actors

import com.franco.moviesdb.database.actors.model.ResponceWithActor
import com.franco.moviesdb.domain.Actor
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface LocalDatasourceActors {

    suspend fun actorsSize(): Int
    suspend fun getAllCastsI(): Flow<List<ResponceWithActor>>
    suspend fun getCastForMovieByIdI(movieId: Int): Flow<List<Actor>>
    suspend fun insertActorList(cast: List<ResponceWithActor>)

}