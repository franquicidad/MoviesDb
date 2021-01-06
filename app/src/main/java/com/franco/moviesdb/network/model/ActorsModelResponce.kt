package com.franco.moviesdb.network.model


import com.google.gson.annotations.SerializedName

data class ActorsModelResponce(
        val cast: List<Cast>,
        val id: Int
)