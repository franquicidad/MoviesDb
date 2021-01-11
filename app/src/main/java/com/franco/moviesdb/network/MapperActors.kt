package com.franco.moviesdb.network

import com.franco.moviesdb.network.model.Actor as NetworkActor
import com.franco.moviesdb.database.actors.model.Actor as DatabaseActor
import com.franco.moviesdb.domain.Actor
import kotlinx.coroutines.flow.Flow
import java.lang.reflect.Array.get

fun NetworkActor.fromNetworkToDatabase(): DatabaseActor =
        DatabaseActor(
                movieId = 0,
                actorId = 0,
                character,
                name,
                order, originalName, profilePath
        )

fun DatabaseActor.fromDatabaseToDomain(): Actor =
        Actor(movieId = 0, actorId, character, name, order, originalName, urlImage = this.profilePath)


fun NetworkActor.fromNetworkToDomain(): Actor =
        Actor(movieId = 0, this.Id, character, name, order, originalName, urlImage = this.profilePath
                ?: "")

fun Actor.fromDomainToDatabase(): DatabaseActor =
        DatabaseActor(
                this.movieId,
                actorId = Id,
                character,
                name,
                order,
                originalName,
                profilePath = this.urlImage)