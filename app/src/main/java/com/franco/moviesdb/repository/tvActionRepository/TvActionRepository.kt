package com.franco.moviesdb.repository.tvActionRepository

import com.franco.moviesdb.domain.MovieActionDomain
import kotlinx.coroutines.flow.Flow

interface TvActionRepository {
    fun getTvListActionByQuery(query: String): Flow<List<MovieActionDomain>>
    suspend fun checkRequireNewPageTvAction(lastVisible: Int)

}