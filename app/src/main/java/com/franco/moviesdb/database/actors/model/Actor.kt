package com.franco.moviesdb.database.actors.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "actorsTable")
data class Actor(
        @PrimaryKey(autoGenerate = false)
        @SerializedName("id")
        val actorId: Int,
        val character: String,
        val name: String,
        val order: Int,
        @SerializedName("original_name")
        val originalName: String,
        @SerializedName("profile_path")
        val profilePath: String

)