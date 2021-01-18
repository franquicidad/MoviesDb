package com.franco.moviesdb.database.actors.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "actorsTable")
data class Actor(
        @PrimaryKey(autoGenerate = false)
        val actorId: Int,
        val movieId: Int? = 0,
        val character: String? = "",
        val name: String? = "",
        val order: Int? = 0,
        val originalName: String? = "",
        val profilePath: String? = "",

)