package com.franco.moviesdb.network.model


import com.google.gson.annotations.SerializedName

data class Cast(
        val id: Int,
        @SerializedName("id")
        val actorId: Int,
        val cast_id: Int,
        val character: String,
        @SerializedName("credit_id")
        val creditId: String,
        val gender: Int,
        @SerializedName("known_for_department")
        val knownForDepartment: String,
        val name: String,
        val order: Int,
        @SerializedName("original_name")
        val originalName: String,

        val popularity: Double,

        @SerializedName("profile_path")
        val profilePath: String
)