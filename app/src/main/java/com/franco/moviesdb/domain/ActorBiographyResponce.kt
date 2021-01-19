package com.franco.moviesdb.domain


data class ActorBiographyResponce(
    val id: Int,
    val biography: String?,
    val birthday: String?,
    val name: String?,
    val placeOfBirth: String?,
    val profilePath: String?
)