package com.franco.moviesdb.network.api

import androidx.room.TypeConverter
import com.franco.moviesdb.network.model.Actor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CastTypeConverter {
    @TypeConverter
    @JvmStatic
    fun fromString(value: String): List<Actor>? {
        val listType = object : TypeToken<List<Actor>>() {}.type
        return Gson().fromJson<List<Actor>>(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromList(list: List<Actor>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}





