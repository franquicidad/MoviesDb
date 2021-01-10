package com.franco.moviesdb.database.tvComedyDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.franco.moviesdb.database.TvActionTable
import com.franco.moviesdb.database.TvComedyTable
import kotlinx.coroutines.flow.Flow


@Dao
interface TvComedyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvComedy(tv: List<TvComedyTable>)

    @Query("SELECT COUNT(id) FROM tv_comedy")
    suspend fun tvCountComedy(): Int

    @Query("SELECT * FROM tv_comedy")
    fun getAllTvComedyByGenre(): Flow<List<TvComedyTable>>

    @Query("SELECT * FROM tv_comedy WHERE title LIKE  '%' ||  :query  ||  '%'  ORDER BY rating DESC")
    fun getListBySearchBarTvComedy(query: String): Flow<List<TvActionTable>>
}