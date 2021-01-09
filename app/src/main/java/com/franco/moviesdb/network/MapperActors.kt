package com.franco.moviesdb.network

import com.franco.moviesdb.database.actors.model.DatabaseActorsModel
import com.franco.moviesdb.database.actors.model.ResponceWithActor
import com.franco.moviesdb.network.model.Cast as ActorCast
import com.franco.moviesdb.database.actors.model.ResponceWithActor as Result
import  com.franco.moviesdb.domain.Actor
import com.franco.moviesdb.database.actors.model.ResponceWithActor as DatabaseCast
import com.franco.moviesdb.network.model.Actor as NetworkCast

fun NetworkCast.fromNetworkToDatabase(): DatabaseCast =
        DatabaseCast(movieId = DatabaseActorsModel(
                movieId = 0,
                cast = Actor(
                        0,
                        character,
                        name,
                        order,
                        originalName,
                        urlImage = ""
                )
        ), actors = character.map {

        })

fun Actor.fromDomainToDatabase(): DatabaseCast =
        DatabaseCast(
                0,
                character,
                name,
                order,
                originalName,
                profilePath = ""
        )

fun DatabaseCast.fromDatabaseToDomain(): Actor =
        Actor(
                0,
                character,
                name,
                order,
                originalName,
                urlImage = ""
        )

fun ActorCast.fromCastToResponce(): ResponceWithActor =
        ResponceWithActor(
                movieId = DatabaseActorsModel(this.id, cast = cast.map {
                    com.franco.moviesdb.database.actors.model.Actor(id, character = it.character, it.name, it.order, it.originalName, it.profilePath)
                }),
                actors = cast.map {
                    com.franco.moviesdb.database.actors.model.Actor(
                            it.Id,
                            character = it.character,
                            name = it.name,
                            order = it.order,
                            originalName = it.originalName,
                            profilePath = it.profilePath

                    )
                }

        )


//fun NetworkCast.fromNetworkToLocal(): List<DatabaseCast> {
//    val movieId = this.movieIdentifier
//    return this.networkCast.map {
//        DatabaseCast(
//                movieId=movieId,
//                actorId =  it.id,
//                character = it.character,
//                name = it.name,
//                order = it.order,
//                originalName = it.originalName,
//                popularity = it.popularity,
//                profilePath = it.profilePath
//        )
//    }
//}
//
//fun Flow<List<DatabaseCast>>.toDomain(): Flow<List<ActorsDomain>> {
//
//    return this.map {
//        it.map {
//            val movieId=it.movieId
//           ActorsDomain(
//                   movieId = movieId ,
//                   actorId = it.actorId,
//                   image = it.profilePath,
//                    name = it.originalName,
//                   character = it.character
//                   )
//        }
//    }
//}






