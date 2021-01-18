package com.franco.moviesdb.domain


data class ActorBiographyResponce(
    val id: Int?=0,
    val biography: String?="",
    val birthday: String?="",
    val deathday: String?="",
    val homepage: String?="",
    val name: String?="",
    val placeOfBirth: String="",
    val profilePath: String=""
)