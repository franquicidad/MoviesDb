package com.franco.moviesdb.database.actors.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "ActorsBiography")
data class ActorBiographyResponce(

        @PrimaryKey(autoGenerate = false)
        val id: Int,
        val biography: String,
        val birthday: String,
        val name: String ,
        val placeOfBirth: String,
        val profilePath: String
)
