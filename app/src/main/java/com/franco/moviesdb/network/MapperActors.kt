package com.franco.moviesdb.network

import  com.franco.moviesdb.domain.ActorsDomain
import  com.franco.moviesdb.network.model.Cast as NetworkCast
import  com.franco.moviesdb.database.ActorsTable as DataBaseCast

fun NetworkCast.fromNetworkToDomain(): ActorsDomain =
        ActorsDomain(id ?: 0, castId ?: 0,
                character ?: "",
                creditId ?: "",
                gender ?: 0,
                knownForDepartment ?: "",
                name ?: "",
                order ?: 0,
                originalName ?: "",
                popularity ?: 0.0,
                profilePath ?: ""
        )

fun DataBaseCast.fromDatabaseToDomain(): ActorsDomain =
        ActorsDomain(id ?: 0, castId ?: 0,
                character ?: "",
                creditId ?: "",
                gender ?: 0,
                knownForDepartment ?: "",
                name ?: "",
                order ?: 0,
                originalName ?: "",
                popularity ?: 0.0,
                profilePath ?: ""
        )

fun ActorsDomain.fromDomainToDatabase(): DataBaseCast =
        DataBaseCast(id ?: 0, castId ?: 0,
                character ?: "",
                creditId ?: "",
                gender ?: 0,
                knownForDepartment ?: "",
                name ?: "",
                order ?: 0,
                originalName ?: "",
                popularity ?: 0.0,
                profilePath ?: ""
        )


