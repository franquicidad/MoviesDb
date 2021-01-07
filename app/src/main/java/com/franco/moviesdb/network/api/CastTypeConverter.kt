package com.franco.moviesdb.network.api

import androidx.room.TypeConverter
import com.franco.moviesdb.network.model.Cast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CastTypeConverter {
    @TypeConverter
    @JvmStatic
    fun fromString(value: String): List<Cast>? {
        val listType = object : TypeToken<List<Cast>>() {}.type
        return Gson().fromJson<List<Cast>>(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromList(list: List<Cast>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}





