package com.franco.moviesdb.domain

import com.google.gson.annotations.SerializedName

class SimilarMovies(
        val id: Int? = 0,
        @SerializedName("poster_path")
        val posterPath: String? = "",
        val title: String? = "",
        val overview: String? = "",
        val voteAverage: Double? = 0.0,

        )