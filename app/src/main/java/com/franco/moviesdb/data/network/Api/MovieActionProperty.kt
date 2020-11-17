package com.franco.moviesdb.data.network.Api

import com.franco.moviesdb.data.Entity.MoviesActionModel

data class MovieActionProperty(
    val page: Int,
    val movieActionList: List<MoviesActionModel>,
    val totalPages: Int,
    val totalResults: Int
) {

}