package com.franco.moviesdb.database.actors.actorsDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.franco.moviesdb.database.actors.model.ActorBiographyResponce
import kotlinx.coroutines.flow.Flow

@Dao
interface ActorsBioDAO {


    @Query("SELECT * FROM actorsbiography WHERE id=:actorId")
    fun getActorBioInfo(actorId: Int): Flow<ActorBiographyResponce>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActorInfo(actor: ActorBiographyResponce)
}