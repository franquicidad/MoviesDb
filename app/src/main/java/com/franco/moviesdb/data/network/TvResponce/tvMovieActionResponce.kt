package com.franco.moviesdb.data.network.TvResponce

import com.franco.moviesdb.data.Entity.TvActionModel
import com.google.gson.annotations.SerializedName


data class TvActionResponce(
    val page: Int,
    val tvActionModels: List<TvActionModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)