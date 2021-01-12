package com.franco.moviesdb.network.model


import com.google.gson.annotations.SerializedName

data class SimilarMovies(
        @SerializedName("backdrop_path")
        val backdropPath: String,

        val id: Int,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val overview: String,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        val title: String,
        @SerializedName("vote_average")
        val voteAverage: Double,

        )