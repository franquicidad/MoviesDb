package com.franco.moviesdb.database.actors.actorsDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.franco.moviesdb.database.actors.model.Actor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Dao
interface ActorsDao {

    //Actor
    @Query("SELECT COUNT(actorId) FROM actorsTable")
    suspend fun actorsCount(): Int

    @Query("SELECT * FROM actorsTable WHERE movieId =:movieId ORDER BY `order` ASC")
    fun getAllActorsByMovieId(movieId: Int): Flow<List<Actor>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actor: List<Actor>)


}