package com.franco.moviesdb.network

import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.util.*
import javax.inject.Inject

class RemoteDatasourceImpl @Inject constructor(
    private val service: ApiService
) : RemoteDatasource {
    override suspend fun getMovieListAction(page: Int): List<MovieActionDomain> =
        service.getMoviesAction(APPEND_MOVIE, ALONE_API, ACTION, page).movieActionList.map {
            it.mapMovieToDomain()
        }

    override suspend fun getMovieListComedy(page: Int): List<MovieActionDomain> =
        service.getMoviesAction(APPEND_MOVIE, ALONE_API, COMEDY, page).movieActionList.map {
            it.mapMovieToDomain()
        }


    override suspend fun getTvListAction(page: Int): List<MovieActionDomain> =
        service.getTvAction(APPEND_TV, ALONE_API, ACTION, page)
            .tvList.map {
                it.mapTvToDomain()
            }

    override suspend fun getTvListComedy(page: Int): List<MovieActionDomain> =
        service.getTvAction(APPEND_TV, ALONE_API, COMEDY, page)
            .tvList.map {
                it.mapTvToDomain()
            }


}