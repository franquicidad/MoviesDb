package com.franco.moviesdb.network.model

import com.franco.moviesdb.network.model.TvActionModel
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