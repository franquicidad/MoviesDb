package com.franco.moviesdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.franco.moviesdb.database.actors.model.Actor
import com.franco.moviesdb.database.actors.actorsDao.ActorsDao
import com.franco.moviesdb.database.moviesActionDao.MoviesActionDao
import com.franco.moviesdb.database.moviesComedyDao.MoviesComedyDao
import com.franco.moviesdb.database.similarMovies.SimilarDao
import com.franco.moviesdb.database.similarMovies.model.SimilarMovies
import com.franco.moviesdb.database.tvActionDao.TvActionDao
import com.franco.moviesdb.database.tvComedyDao.TvComedyDao
import com.franco.moviesdb.network.api.CastTypeConverter

@Database(
        entities = [
            MovieActionTable::class,
            MovieComedyTable::class,
            TvActionTable::class,
            TvComedyTable::class,
            Actor::class,
            SimilarMovies::class
        ], version = 14
)
@TypeConverters(CastTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun moviesActionDAO(): MoviesActionDao
    abstract fun moviesComedyDAO(): MoviesComedyDao
    abstract fun tvActionDAO(): TvActionDao
    abstract fun tvComedyDAO(): TvComedyDao
    abstract fun actorsDAO(): ActorsDao
    abstract fun similarDAO(): SimilarDao
}
//
//val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        // Since we didn't alter the table, there's nothing else to do here.
//    }
//}



