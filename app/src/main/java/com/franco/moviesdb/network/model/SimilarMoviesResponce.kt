package com.franco.moviesdb.network.model


import com.google.gson.annotations.SerializedName

data class SimilarMoviesResponce(
        val page: Int,
        val similarMovies: List<SimilarMovies>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)