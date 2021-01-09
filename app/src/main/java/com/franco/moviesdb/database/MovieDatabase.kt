package com.franco.moviesdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.franco.moviesdb.database.actors.model.Actor
import com.franco.moviesdb.database.actors.model.DatabaseActorsModelWithActor
import com.franco.moviesdb.database.moviesAction.MoviesAndTvDao
import com.franco.moviesdb.network.api.CastTypeConverter

@Database(
        entities = [
            MovieActionTable::class,
            MovieComedyTable::class,
            TvActionTable::class,
            TvComedyTable::class,
            DatabaseActorsModelWithActor::class,
//            ActorsModelResponceDb::class,
            Actor::class
        ], version = 12
)
@TypeConverters(CastTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun moviesDAO(): MoviesAndTvDao
}
//
//val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        // Since we didn't alter the table, there's nothing else to do here.
//    }
//}



