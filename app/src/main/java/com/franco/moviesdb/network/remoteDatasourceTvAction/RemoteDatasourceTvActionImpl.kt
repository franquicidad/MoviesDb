package com.franco.moviesdb.network.remoteDatasourceTvAction

import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.network.mapTvToDomain
import com.franco.moviesdb.util.ACTION
import com.franco.moviesdb.util.ALONE_API
import com.franco.moviesdb.util.APPEND_TV
import javax.inject.Inject

class RemoteDatasourceTvActionImpl @Inject constructor(
        private val service: ApiService
) : RemoteDatasourceTvAction {
    override suspend fun getTvListAction(page: Int): List<MovieActionDomain> =
            service.getTvAction(APPEND_TV, ALONE_API, ACTION, page)
                    .tvList.map {
                        it.mapTvToDomain()
                    }

}