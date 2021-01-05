package com.franco.moviesdb.domain

import com.google.gson.annotations.SerializedName

data class MovieActionDomain(
        @SerializedName("id")
        val id: Int?,

        @SerializedName("backdrop_path")
        val backdropPath: String?,

        @SerializedName("title")
        var title: String?,

        @SerializedName("original_language")
        val originalLanguage: String?,

        val overview: String?,

        @SerializedName("poster_path")
        val posterPath: String?,

        @SerializedName("release_date")
        val releaseDate: String?,

        @SerializedName("vote_average")
        val rating: Double?,
)