package com.franco.moviesdb.domain

data class Actor(
        var movieId: Int,
        val Id: Int,
        val character: String,
        val name: String,
        val order: Int,
        val originalName: String,
        val urlImage: String,
)
