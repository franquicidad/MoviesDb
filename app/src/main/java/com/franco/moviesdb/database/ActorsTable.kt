package com.franco.moviesdb.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "actors")
data class ActorsTable(

        @PrimaryKey(autoGenerate = false)
        val id: Int,

        @SerializedName("cast_id")
        val castId: Int,

        val character: String,

        @SerializedName("credit_id")
        val creditId: String,

        val gender: Int,

        @SerializedName("known_for_department")
        val knownForDepartment: String,

        val name: String,

        val order: Int,

        @SerializedName("original_name")
        val originalName: String,

        val popularity: Double,
        @SerializedName("profile_path")

        val profilePath: String

)