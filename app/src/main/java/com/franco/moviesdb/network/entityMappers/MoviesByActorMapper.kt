package com.franco.moviesdb.network.entityMappers

import com.franco.moviesdb.domain.Actor
import com.franco.moviesdb.database.actors.model.ActorListMovies as Database
import com.franco.moviesdb.network.model.ActorListMovies as Network
import com.franco.moviesdb.domain.ActorListMovies

fun Database.fromDatabaseToDomain(): ActorListMovies =
        ActorListMovies(
                id, backdropPath, character, actorId, originalLanguage, overview, posterPath, releaseDate
                ?: "", title, rating
        )

fun ActorListMovies.fromDomainToDatabase(): Database =
        Database(
                id,
                backdropPath ?: "",
                character ?: "",
                actorId = actorId ?: 0,
                originalLanguage ?: "",
                overview ?: "",
                posterPath ?: "",
                releaseDate ?: "",
                title ?: "",
                rating ?: 0.0

        )

fun Network.fromNetworkToDomain(): ActorListMovies =
        ActorListMovies(
                id, backdropPath, character, actorId = 0, originalLanguage, overview, posterPath
                ?: "", releaseDate
                ?: "", title, rating
        )