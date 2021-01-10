package com.franco.moviesdb.database.moviesComedyDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.franco.moviesdb.database.MovieComedyTable
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesComedyDao {
    @Query("SELECT * FROM movie_comedy")

    fun getAllMoviesComedyByGenre(): Flow<List<MovieComedyTable>>

    @Query("SELECT * FROM movie_comedy WHERE title LIKE  '%' ||  :query  ||  '%'  ORDER BY releaseDate DESC")
    fun getListBySearchBarMovieComedy(query: String): Flow<List<MovieComedyTable>>

    @Query("SELECT COUNT(id) FROM movie_comedy")
    suspend fun movieCountComedy(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieComedy(movie: List<MovieComedyTable>)

}