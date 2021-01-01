package com.franco.moviesdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.franco.moviesdb.database.moviesAction.MoviesAndTvDao

@Database(
    entities = [
        MovieActionTable::class,
        MovieComedyTable::class,
        TvActionTable::class,
        TvComedyTable::class
    ], version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun moviesDAO(): MoviesAndTvDao
}



