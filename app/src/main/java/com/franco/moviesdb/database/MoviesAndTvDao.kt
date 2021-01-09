package com.franco.moviesdb.database.moviesAction

import androidx.room.*
import com.franco.moviesdb.database.*
import com.franco.moviesdb.database.actors.model.Actor
import com.franco.moviesdb.database.actors.model.ResponceWithActor
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow


@Dao
interface MoviesAndTvDao {

    @Query("SELECT * FROM movie_action")
    fun getAllMoviesActionByGenre(): Flow<List<MovieActionTable>>

    @Query("SELECT * FROM movie_comedy")
    fun getAllMoviesComedyByGenre(): Flow<List<MovieComedyTable>>

    @Query("SELECT * FROM tv_action")
    fun getAllTvActionByGenre(): Flow<List<TvActionTable>>

    @Query("SELECT * FROM tv_comedy")
    fun getAllTvComedyByGenre(): Flow<List<TvComedyTable>>

    //Actor
    @Query("SELECT COUNT(id) FROM actorsTable")
    suspend fun actorsCount(): Int

    @Query("SELECT * FROM actorsTable WHERE id = :id")
    fun getAllActorsByMovieId(id: Int): List<Actor>

    @Query("SELECT * FROM actorsTable WHERE id IN (SELECT actorId FROM DatabaseActorsModelWithActor WHERE movieId = :movieId)")
    suspend fun getActorsForMovie(movieId: Int): Flow<List<Actor>>

    @Query("SELECT * FROM databaseactorsmodelwithactor ")
    fun getAllCast(): Flow<List<ResponceWithActor>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actor: List<ResponceWithActor>)

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
