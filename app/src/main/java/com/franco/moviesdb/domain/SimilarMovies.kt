package com.franco.moviesdb.domain

import com.google.gson.annotations.SerializedName

class SimilarMovies(
        val id: Int = 0,
        @SerializedName("poster_path")
        val posterPath: String = "",
        @SerializedName("backdrop_path")
        val backdropPath: String = "",
        val title: String = "",
        val overview: String = "",
        val releaseDate: String = "",
        val originalLanguage: String = "",
        val rating: Double? = 0.0,

        )