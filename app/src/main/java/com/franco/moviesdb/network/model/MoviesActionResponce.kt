package com.franco.moviesdb.network.model

import com.franco.moviesdb.network.model.MoviesActionModel
import com.google.gson.annotations.SerializedName

data class MoviesActionResponce(
        val page: Int,
        @SerializedName("results")
        val movieActionList: List<MoviesActionModel>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)