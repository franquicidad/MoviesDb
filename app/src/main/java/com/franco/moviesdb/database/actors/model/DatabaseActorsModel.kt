package com.franco.moviesdb.database.actors.model

import com.franco.moviesdb.database.actors.model.Actor

data class DatabaseActorsModel(
        val movieId: Int,
        val cast: List<Actor>
)


