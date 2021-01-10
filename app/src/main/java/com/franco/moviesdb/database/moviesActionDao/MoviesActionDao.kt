package com.franco.moviesdb.database.moviesActionDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.franco.moviesdb.database.MovieActionTable
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesActionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieAction(movie: List<MovieActionTable>)

    @Query("SELECT COUNT(id) FROM movie_action")
    suspend fun movieCountAction(): Int

    @Query("SELECT * FROM movie_action")
    fun getAllMoviesActionByGenre(): Flow<List<MovieActionTable>>

    @Query("SELECT * FROM movie_action WHERE title LIKE  '%' ||  :query  ||  '%'  ORDER BY releaseDate DESC")
    fun getListBySearchBarMovieAction(query: String): Flow<List<MovieActionTable>>
}