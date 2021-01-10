package com.franco.moviesdb.repository.tvComedyRepository

import com.franco.moviesdb.domain.MovieActionDomain
import kotlinx.coroutines.flow.Flow

interface TvComedyRepository {
    fun getTvListComedyByQuery(query: String): Flow<List<MovieActionDomain>>
    suspend fun checkRequireNewPageTvComedy(lastVisible: Int)

}