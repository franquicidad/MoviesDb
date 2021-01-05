package com.franco.moviesdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.franco.moviesdb.database.moviesAction.MoviesAndTvDao


@Database(
        entities = [
            MovieActionTable::class,
            MovieComedyTable::class,
            TvActionTable::class,
            TvComedyTable::class
        ], version = 6
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun moviesDAO(): MoviesAndTvDao
}
//
//val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        // Since we didn't alter the table, there's nothing else to do here.
//    }
//}



