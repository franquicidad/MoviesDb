package com.franco.moviesdb.database.similarMovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class SimilarMovies(
        @PrimaryKey(autoGenerate = false)
        val id: Int? = 0,
        val relatedToMovieId: Int? = 0,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        val originalLanguage: String,
        val releaseDate: String,
        val posterPath: String? = "",
        val title: String? = "",
        val overview: String = "",
        val voteAverage: Double? = 0.0

)