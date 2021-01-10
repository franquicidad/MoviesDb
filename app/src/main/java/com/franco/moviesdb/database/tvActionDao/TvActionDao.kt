package com.franco.moviesdb.database.tvActionDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.franco.moviesdb.database.TvActionTable
import kotlinx.coroutines.flow.Flow

@Dao
interface TvActionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvAction(tv: List<TvActionTable>)

    @Query("SELECT COUNT(id) FROM tv_action")
    suspend fun tvCountAction(): Int

    @Query("SELECT * FROM tv_action")
    fun getAllTvActionByGenre(): Flow<List<TvActionTable>>

    @Query("SELECT * FROM tv_action WHERE title LIKE  '%' ||  :query  ||  '%'  ORDER BY rating DESC")
    fun getListBySearchBarTvAction(query: String): Flow<List<TvActionTable>>
}