package com.franco.moviesdb.database.actors

import com.franco.moviesdb.domain.Actor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface LocalDatasourceActors {

    suspend fun actorsSize(): Int
    suspend fun getAllActorsByMovieId(movieId: Int): Flow<List<Actor>>
    suspend fun insertActorList(cast: List<Actor>)

}