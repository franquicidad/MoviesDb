package com.franco.moviesdb.domain

import com.google.gson.annotations.SerializedName

data class ActorListMovies(
        val id: Int,
        val backdropPath: String,
        val character: String,
        val originalLanguage: String,
        val overview: String,
        val posterPath: String,
        val releaseDate: String,
        val title: String,
        val rating: Double?,
)