package com.franco.moviesdb.network.model

data class ActorPresentMovies(
        val cast: List<ActorListMovies>,
        val crew: List<Crew>,
        val id: Int
)