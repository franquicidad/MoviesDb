package com.franco.moviesdb.data.network.TvResponce

import com.franco.moviesdb.database.TvActionModel
import com.google.gson.annotations.SerializedName


data class TvActionResponce(
        val page: Int,
        @SerializedName("results")
        val tvList: List<TvActionModel>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)