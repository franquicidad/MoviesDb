package com.franco.moviesdb.database.similarMovies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.franco.moviesdb.database.actors.model.Actor
import com.franco.moviesdb.database.similarMovies.model.SimilarMovies
import kotlinx.coroutines.flow.Flow

@Dao
interface SimilarDao {

    @Query("SELECT COUNT(id) FROM SimilarMovies")
    suspend fun similarCount(): Int

    @Query("SELECT * FROM similarmovies WHERE relatedToMovieId =:movieId ")
    fun getAllSimilarByMovieId(movieId: Int): Flow<List<SimilarMovies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSimilar(movies: List<SimilarMovies>)
}