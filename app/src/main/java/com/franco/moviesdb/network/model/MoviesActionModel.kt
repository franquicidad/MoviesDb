package com.franco.moviesdb.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MoviesActionModel(

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    val rating: Double,


    )