package com.franco.moviesdb.network.entityMappers

import com.franco.moviesdb.domain.ActorBiographyResponce
import com.franco.moviesdb.database.actors.model.ActorBiographyResponce as DataBase
import com.franco.moviesdb.network.model.ActorBiographyResponce as Network

fun DataBase.fromDatabaseToDomain():ActorBiographyResponce=
        ActorBiographyResponce(
                id, biography, birthday, deathday, homepage, name, placeOfBirth, profilePath
        )

fun ActorBiographyResponce.fromDomainToDatabase(): DataBase =
    DataBase(
        id,
            biography?:"",
            birthday?:"",
deathday?:"",
            homepage?:"",
            name?:"",
            placeOfBirth?:"",
            profilePath?:""
    )



fun Network.fromNetworkToDomain(): ActorBiographyResponce =
    ActorBiographyResponce(
        id,
            biography?:"",
            birthday?:"",
            deathday?:"",
            homepage?:"",
            name?:"",
            placeOfBirth?:"",
            profilePath?:""
    )