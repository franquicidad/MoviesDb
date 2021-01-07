package com.franco.moviesdb.network.model

import com.google.gson.annotations.SerializedName

class NetworkActorsResponce(
        val id: Int,
        @SerializedName("cast")
        val networkCast: List<Cast>
)