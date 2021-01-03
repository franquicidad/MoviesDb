package com.franco.moviesdb.network

import android.util.Log
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.util.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class RemoteDatasourceImpl @Inject constructor(
    private val service: ApiService
) : RemoteDatasource {
    init {
//         GlobalScope.async {
//             val list=service.getMoviesComedy(APPEND_MOVIE, POPULARITY, ALONE_API, COMEDY,1)
//             Log.i("comList","${list.movieComedyList}")
//
//         }

    }

    override suspend fun getMovieListAction(page: Int): List<MovieActionDomain> =
        service.getMoviesAction(
            APPEND_MOVIE,
            POPULARITY,
            ALONE_API,
            ACTION,
            page
        ).movieActionList.map {
            it.mapMovieToDomain()
        }

    override suspend fun getMovieListComedy(page: Int): List<MovieActionDomain> =
        service.getMoviesComedy(
            APPEND_MOVIE, POPULARITY,
            ALONE_API, COMEDY, page
        )
            .movieActionList.map { movieComedy ->
                movieComedy.mapMovieToDomain()

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