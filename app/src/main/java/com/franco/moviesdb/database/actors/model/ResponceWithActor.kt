package com.franco.moviesdb.database.actors.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.franco.moviesdb.database.actors.model.Actor
import com.franco.moviesdb.database.actors.model.DatabaseActorsModel
import com.franco.moviesdb.database.actors.model.DatabaseActorsModelWithActor

data class ResponceWithActor(
        @Embedded val movieId: DatabaseActorsModel,
        @Relation(
                parentColumn = "movieId",
                entityColumn = "actorId",
                associateBy = Junction(DatabaseActorsModelWithActor::class)
        )
        val actors: List<Actor>,
)