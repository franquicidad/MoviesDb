package com.franco.moviesdb.database.actors.actorListMovie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.franco.moviesdb.database.actors.model.Actor
import com.franco.moviesdb.database.actors.model.ActorListMovies
import kotlinx.coroutines.flow.Flow

@Dao
interface ActorListMovieDao {

    @Query("SELECT * FROM actorlistmovies WHERE actorId =:actorId ")
    fun getAllMoviesByActorId(actorId: Int): Flow<List<ActorListMovies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actor: List<ActorListMovies>)
}