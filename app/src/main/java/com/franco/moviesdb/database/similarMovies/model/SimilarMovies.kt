package com.franco.moviesdb.database.similarMovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class SimilarMovies(
        @PrimaryKey(autoGenerate = false)
        val id: Int,
        var relatedToMovieId: Int?,
        val originalName: String?,
        val backdropPath: String?,
        val originalLanguage: String?,
        val releaseDate: String?,
        val posterPath: String?,
        val title: String?,
        val overview: String?,
        val rating: Double?

)