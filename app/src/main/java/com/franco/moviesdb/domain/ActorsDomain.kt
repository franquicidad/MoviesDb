package com.franco.moviesdb.domain

import com.franco.moviesdb.network.model.Cast
import com.google.gson.annotations.SerializedName

data class ActorsDomain(
        val id: Int,
        val image: String,
        val name: String,
        val character: String
)
