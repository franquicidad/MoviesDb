package com.franco.moviesdb.network.model


import com.google.gson.annotations.SerializedName

data class Actor(
        @SerializedName("id")
        val Id: Int? = 0,
        val character: String? = "",
        val name: String? = "",
        val order: Int? = 0,
        @SerializedName("original_name")
        val originalName: String? = "",
        @SerializedName("profile_path")
        val profilePath: String? = ""
)