package com.franco.moviesdb.database


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.franco.moviesdb.network.api.CastTypeConverter
import com.franco.moviesdb.network.model.Cast

@Entity(tableName = "actorsByMovie")
data class ActorsModelResponceDb(
        @PrimaryKey(autoGenerate = false)
        val id: Int,
        val cast: List<Cast>

)