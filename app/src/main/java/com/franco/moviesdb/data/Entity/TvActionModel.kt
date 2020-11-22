package com.franco.moviesdb.data.Entity

import com.google.gson.annotations.SerializedName

data class TvActionModel(

    val id: Int,

    val name: String,

    val first_air_date: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_name")
    val originalName: String,

    val overview: String,

    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("vote_average")
    val rating: Double,

    @SerializedName("vote_count")
    val voteCount: Int,


    )