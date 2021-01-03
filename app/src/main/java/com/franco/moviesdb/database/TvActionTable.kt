package com.franco.moviesdb.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tv_action")
data class TvActionTable(
    @PrimaryKey(autoGenerate = false)
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

@Entity(tableName = "tv_comedy")
data class TvComedyTable(
    @PrimaryKey(autoGenerate = false)
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