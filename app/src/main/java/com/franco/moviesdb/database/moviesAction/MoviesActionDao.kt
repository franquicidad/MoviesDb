package com.franco.moviesdb.database.moviesAction

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MoviesActionDao {
    @Query("SELECT * FROM movie_action")
    suspend fun getAllMoviesAction(): LiveData<List<MovieActionDatabase>>
}

@Database(entities = [MoviesActionModel::class], version = 1)
abstract class MovieActionDatabase : RoomDatabase() {
    abstract val moviesActionDao: MoviesActionDao

}

private lateinit var INSTANCE: MovieActionDatabase
fun getDatabase(context: Context): MovieActionDatabase {
    synchronized(MovieActionDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                    MovieActionDatabase::class.java,
                    "movie_action").build()
        }
    }
    return INSTANCE
}