package com.franco.moviesdb.network.api

import com.franco.moviesdb.network.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
//https://api.themoviedb.org/3/discover/movie?api_key=7d51874568317dfd0c91db399be2bdec&with_genres=28  ActionMovies

    @GET("discover/{sort_criteria}")
    suspend fun getMoviesAction(
        @Path("sort_criteria") sort_criteria: String,
        @Query("sort_by") sort_by: String?,
        @Query("api_key") api_key: String?,
        @Query("with_genres") genre: Int?,
        @Query("page") page: Int?,

        ): MoviesActionResponce

    @GET("discover/{sort_criteria}")
    suspend fun getMoviesComedy(
        @Path("sort_criteria") sort_criteria: String,
        @Query("sort_by") sort_by: String?,
        @Query("api_key") api_key: String?,
        @Query("with_genres") genre: Int?,
        @Query("page") page: Int?,

        ): MoviesActionResponce

    @GET("discover/{sort_criteria}")
    suspend fun getTvAction(
        @Path("sort_criteria") sort_criteria: String,
        @Query("api_key") api_key: String?,
        @Query("with_genres") genre: Int?,
        @Query("page") page: Int?,

        ): TvActionResponce


//https://api.themoviedb.org/3/movie/popular?api_key=7d51874568317dfd0c91db399be2bdec&language=en-US&page=1

    @GET("{sort_criteria}/popular")
    suspend fun getMoviesOrTvPopular(
            @Path("sort_criteria") sort_criteria: String,
            @Query("api_key") api_key: String?,
            @Query("language") language: String? = "en-US",
            @Query("page") page: Int? = 1,

            ): MoviesActionResponce

    //actors per movie
    //https://api.themoviedb.org/3/movie/464052/credits?api_key=7d51874568317dfd0c91db399be2bdec&language=en-US

    @GET("{movie}/{movie_id}/credits")
    suspend fun getActorsByMovie(
        @Path("movie") movieOrTv: String,
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String,
    ): Cast


    //https://api.themoviedb.org/3/movie/464052/similar?api_key=7d51874568317dfd0c91db399be2bdec&language=en-US&page=1

    @GET("{movie}/{movie_id}/similar")
    suspend fun getSimilarMovies(
            @Path("movie") movieOrTv: String,
            @Path("movie_id") movieId: Int,
            @Query("api_key") api_key: String,
            @Query("language") language: String = "en-US",
            @Query("page") page: Int?,
    ): SimilarMoviesResponce

    //https://api.themoviedb.org/3/person/935?api_key=7d51874568317dfd0c91db399be2bdec&language=en-US
    @GET("person/{actor_id}")
    suspend fun getActorBiography(
            @Path("actor_id") movieId: Int,
            @Query("api_key") api_key: String,
            @Query("language") language: String = "en-US",
    ): ActorBiographyResponce

    @GET("person/{actor_id}/movie_credits")
    suspend fun getMoviesFromActor(
            @Path("actor_id") actorId: Int,
            @Query("api_key") api_key: String,
            @Query("language") language: String = "en-US",
    ): ActorPresentMovies

}




