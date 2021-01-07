package com.franco.moviesdb.network

import com.franco.moviesdb.database.ActorsTable
import  com.franco.moviesdb.domain.ActorsDomain
import com.franco.moviesdb.network.model.Cast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import  com.franco.moviesdb.network.model.NetworkActorsResponce as NetworkCast
import  com.franco.moviesdb.database.ActorsTable as DataBaseCast

fun NetworkCast.fromNetworkToLocal(): List<ActorsTable> {
    val movieId = this.id
    return this.networkCast.map {
        ActorsTable(
                id = it.id,
                movieId = movieId,
                castId = it.cast_id,
                character = it.character,
                creditId = it.creditId,
                gender = it.gender,
                knownForDepartment = it.knownForDepartment,
                name = it.name,
                order = it.order,
                originalName = it.originalName,
                popularity = it.popularity,
                profilePath = it.profilePath
        )
    }
}

fun Flow<List<ActorsTable>>.toDomain(): Flow<List<ActorsDomain>> {
    return this.map {
        it.map {
            ActorsDomain(
                    id = it.id,
                    character = it.character,
                    name = it.name,
                    image = it.profilePath
            )
        }
    }
}






