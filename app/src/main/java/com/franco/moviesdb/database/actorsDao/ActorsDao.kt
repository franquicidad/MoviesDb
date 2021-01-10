package com.franco.moviesdb.database.actorsDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.franco.moviesdb.database.actors.model.Actor
import com.franco.moviesdb.database.actors.model.ResponceWithActor
import kotlinx.coroutines.flow.Flow

@Dao
interface ActorsDao {

    //Actor
    @Query("SELECT COUNT(actorId) FROM actorsTable")
    suspend fun actorsCount(): Int

    @Query("SELECT * FROM actorsTable WHERE actorId = :id")
    fun getAllActorsByMovieId(id: Int): List<Actor>

    @Query("SELECT * FROM actorsTable WHERE actorId IN (SELECT actorId FROM DatabaseActorsModelWithActor WHERE movieId = :movieId)")
    suspend fun getActorsForMovie(movieId: Int): Flow<List<Actor>>

    @Query("SELECT * FROM databaseactorsmodelwithactor ")
    fun getAllCast(): Flow<List<ResponceWithActor>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actor: List<ResponceWithActor>)

}