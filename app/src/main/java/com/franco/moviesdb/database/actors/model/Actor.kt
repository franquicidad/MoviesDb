package com.franco.moviesdb.database.actors.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "actorsTable")
data class Actor(
        @PrimaryKey(autoGenerate = false)
        @SerializedName("id")
        val actorId: Int,
        @SerializedName("id")
        val movieId: Int? = 0,
        val character: String? = "",
        val name: String? = "",
        val order: Int? = 0,
        @SerializedName("original_name")
        val originalName: String? = "",
        @SerializedName("profile_path")
        val profilePath: String? = ""

)