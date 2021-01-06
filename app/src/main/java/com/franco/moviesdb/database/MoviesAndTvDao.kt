package com.franco.moviesdb.database.moviesAction

import androidx.room.*
import com.franco.moviesdb.database.*
import kotlinx.coroutines.flow.Flow


@Dao
interface MoviesAndTvDao {

    @Query("SELECT * FROM movie_action")
    fun getAllMoviesActionByGenre(): Flow<List<MovieActionTable>>

    @Query("SELECT * FROM movie_action")
    fun getAllMoviesComedyByGenre(): Flow<List<MovieComedyTable>>

    @Query("SELECT * FROM tv_action")
    fun getAllTvActionByGenre(): Flow<List<TvActionTable>>

    @Query("SELECT * FROM tv_action")
    fun getAllTvComedyByGenre(): Flow<List<TvComedyTable>>

    //Actors
    @Query("SELECT * FROM actors WHERE :id")
    fun getAllActorsMovieId(id: Int): Flow<List<ActorsTable>>

    @Query("SELECT COUNT(id) FROM actors")
    suspend fun actorsCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actors: List<ActorsTable>)

    //Querys

    @Query("SELECT * FROM movie_action WHERE title LIKE  '%' ||  :query  ||  '%'  ORDER BY releaseDate DESC")
    fun getListBySearchBarMovieAction(query: String): Flow<List<MovieActionTable>>

    @Query("SELECT * FROM movie_comedy WHERE title LIKE  '%' ||  :query  ||  '%'  ORDER BY releaseDate DESC")
    fun getListBySearchBarMovieComedy(query: String): Flow<List<MovieComedyTable>>

    @Query("SELECT * FROM tv_action WHERE title LIKE  '%' ||  :query  ||  '%'  ORDER BY rating DESC")
    fun getListBySearchBarTvAction(query: String): Flow<List<TvActionTable>>

    @Query("SELECT * FROM tv_comedy WHERE title LIKE  '%' ||  :query  ||  '%'  ORDER BY rating DESC")
    fun getListBySearchBarTvComedy(query: String): Flow<List<TvActionTable>>

    @Query("SELECT COUNT(id) FROM movie_action")
    suspend fun movieCountAction(): Int

    @Query("SELECT COUNT(id) FROM movie_comedy")
    suspend fun movieCountComedy(): Int

    @Query("SELECT COUNT(id) FROM tv_action")
    suspend fun tvCountAction(): Int

    @Query("SELECT COUNT(id) FROM tv_comedy")
    suspend fun tvCountComedy(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieAction(movie: List<MovieActionTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieComedy(movie: List<MovieComedyTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvAction(tv: List<TvActionTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvComedy(tv: List<TvComedyTable>)
}
