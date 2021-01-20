package com.franco.moviesdb.database.actors.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ActorListMovies(
        @PrimaryKey(autoGenerate = false)
        val id: Int,
        val backdropPath: String,
        val character: String,
        val actorId: Int?,
        val originalLanguage: String,
        val overview: String,
        val posterPath: String,
        val releaseDate: String,
        val title: String,
        val rating: Double,
)

