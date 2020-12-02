package com.franco.moviesdb.data.network.MovieResponce

import androidx.room.Entity
import com.franco.moviesdb.database.moviesAction.MoviesActionModel
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies_action")
data class MoviesActionResponce(
        val page: Int,
        @SerializedName("results")
        val movieActionList: List<MoviesActionModel>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)