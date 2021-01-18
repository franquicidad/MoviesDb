package com.franco.moviesdb.network.model


import com.google.gson.annotations.SerializedName

data class ActorBiographyResponce(
    val id: Int,
    val biography: String?="",
    val birthday: String="",
    val deathday: String="",
    val homepage: String="",
    val name: String="",
    @SerializedName("place_of_birth")
    val placeOfBirth: String="",
    @SerializedName("profile_path")
    val profilePath: String=""
)