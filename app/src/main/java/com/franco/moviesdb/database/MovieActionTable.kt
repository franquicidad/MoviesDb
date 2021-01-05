package com.franco.moviesdb.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_action")
data class MovieActionTable(

        @PrimaryKey(autoGenerate = false)
        @SerializedName("id")
        val id: Int?,

        @SerializedName("backdrop_path")
        val backdropPath: String?,

        @SerializedName("title")
        var title: String?,

        @SerializedName("original_language")
        val originalLanguage: String?,

        val overview: String?,

        @SerializedName("poster_path")
        val posterPath: String?,

        @SerializedName("release_date")
        val releaseDate: String?,

        @SerializedName("vote_average")
        val rating: Double?,

        )

