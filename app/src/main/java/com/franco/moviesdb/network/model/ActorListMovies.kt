package com.franco.moviesdb.network.model

import com.google.gson.annotations.SerializedName

data class ActorListMovies(
        val id: Int,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        val character: String,
        @SerializedName("original_language")
        val originalLanguage: String,
        val overview: String,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        val title: String,
        @SerializedName("vote_average")
        val rating: Double?,
)