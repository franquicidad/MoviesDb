package com.franco.moviesdb.network.model

import com.google.gson.annotations.SerializedName

data class MoviesComedyResponce(
    val page: Int,
    @SerializedName("results")
    val movieComedyList: List<MoviesComedyModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)