package com.franco.moviesdb.network.remoteDatasourceTvComedy

import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.network.mapTvToDomain
import com.franco.moviesdb.util.ALONE_API
import com.franco.moviesdb.util.APPEND_TV
import com.franco.moviesdb.util.COMEDY
import javax.inject.Inject

class RemoteDataSourceTvComedyImpl @Inject constructor(
        private val service: ApiService
) : RemoteDataSourceTvComedy {
    override suspend fun getTvListComedy(page: Int): List<MovieActionDomain> =
            service.getTvAction(APPEND_TV, ALONE_API, COMEDY, page)
                    .tvList.map {
                        it.mapTvToDomain()
                    }
}