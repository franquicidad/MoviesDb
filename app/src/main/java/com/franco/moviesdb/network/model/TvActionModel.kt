package com.franco.moviesdb.network.model


import com.google.gson.annotations.SerializedName

data class TvActionModel(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val title: String?,

    @SerializedName("first_air_date")
    val releaseDate: String?,

    @SerializedName("original_language")
    val originalLanguage: String?,

//        @SerializedName("original_name")
//        val originalName: String,

    val overview: String?,

//        val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("vote_average")
    val rating: Double?,

//        @SerializedName("vote_count")
//        val voteCount: Int,


)